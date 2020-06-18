package com.city.common.zkclient.master;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MasterSelector {

    private ZkClient zkClient;

    //需要争抢的节点
    private final static String MASTER_PATH="/master";

    //注册节点变化监听器
    private IZkDataListener dataListener;

    //其他服务器
    private UserCenter server;

    //主服务器
    private UserCenter master;

    private static boolean isRunning = false;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    //传入当前谁获得了master
    public MasterSelector(ZkClient zkClient,UserCenter server) {
        this.zkClient = zkClient;
        this.server = server;
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //节点被删除,发起选组操作
                chooseMaster();
            }
        };
    }

    public void start(){
        if(!isRunning){
            isRunning=true;
            //注册节点事件
            zkClient.subscribeDataChanges(MASTER_PATH,dataListener);
            chooseMaster();
        }
    }


    public void stop(){
        if(isRunning){
            isRunning = false;
            scheduledExecutorService.shutdown();
            zkClient.unsubscribeDataChanges(MASTER_PATH,dataListener);
            releaseMaster();
        }
    }


    //选主操作
    private void chooseMaster(){
        if(!isRunning){
            System.out.println("服务未启动");
            return;
        }
        try {
            zkClient.createEphemeral(MASTER_PATH,server);
            master=server;
            System.out.println(master+"->我现在已经是master了,你们都要听我的");

            //定时器 模式故障 节点故障
            scheduledExecutorService.schedule(()->{
                //释放
                releaseMaster();
            },1, TimeUnit.SECONDS);
        }catch (Exception e){
            //写入节点失败,master节点已经存在
            UserCenter userCenter = zkClient.readData(MASTER_PATH,true);
            if(userCenter == null){
                chooseMaster();
            }else{
                master = userCenter;
            }
        }
    }

    //释放锁（故障模拟）
    private void releaseMaster(){
        //判断当前是不是master,master才需要释放
        if(checkIsMaster()){
            zkClient.delete(MASTER_PATH);
            //标志master节点故障
            master.setFlag(false);
        }
    }

    //判断当前是不是master
    private boolean checkIsMaster(){
        UserCenter userCenter = zkClient.readData(MASTER_PATH);
        if(userCenter.getPcName().equals(server.getPcName())){
            //这里需不需要其他操作,未必需要
            return true;
        }
        return false;
    }



}
