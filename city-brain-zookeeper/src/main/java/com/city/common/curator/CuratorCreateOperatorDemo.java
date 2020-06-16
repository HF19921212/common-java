package com.city.common.curator;

import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuratorCreateOperatorDemo {

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
        System.out.println("链接成功...........");

        //添加
        try {
            java.lang.String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                    forPath("/curator/curator-1/curator-1-1","123".getBytes());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //删除
        try {
            //默认情况下,version为-1
            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查询
        try {
            Stat stat = new Stat();
            byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/curator");
            System.out.println(new java.lang.String(bytes) + " -> stat："+stat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //修改
        try {
            Stat stat = curatorFramework.setData().forPath("/curator","123".getBytes());
            System.out.println(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //异步操作
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).
                    inBackground(new BackgroundCallback() {
                        @Override
                        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                            System.out.println(Thread.currentThread().getName()+" ->resultCode: "+curatorEvent.getResultCode()+" -> "
                            +curatorEvent.getType());
                            countDownLatch.countDown();
                        }
                    },executorService).forPath("/curatorback","123".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        executorService.shutdown();

        //事务操作（curator私有的）
        try {
            Collection<CuratorTransactionResult> resultCollections = curatorFramework.inTransaction().create().forPath("/trans","111".getBytes()).and().
                    setData().forPath("/curator","111".getBytes()).
                    and().commit();
            for(CuratorTransactionResult result : resultCollections){
                System.out.println(result.getForPath()+"->"+result.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
