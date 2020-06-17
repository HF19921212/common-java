package com.city.common.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//分布式锁
public class DistributedLock {

    //根节点
    private static final String ROOT_LOCKS="/Locks";
    private ZooKeeper zooKeeper;
    private int sessionTimeout;
    //记录锁id
    private String lockId;
    //节点数据
    private final static byte[] data = {1,2};
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public DistributedLock() throws IOException, InterruptedException {
        this.zooKeeper = ZookeeperClient.getInstance();
        this.sessionTimeout = ZookeeperClient.getSessionTimeout();
    }

    //获取锁方法
    public boolean lock(){
        try {
            //LOCKS 0000000001
            lockId = zooKeeper.create(ROOT_LOCKS+"/",data,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);//创建临时的有序节点
            System.out.println(Thread.currentThread().getName()+"->创建了lock节点["+lockId+"],开始竞争锁");

            //获取根节点下的所有子节点
            List<String> childrenNodes = zooKeeper.getChildren(ROOT_LOCKS,true);
            //从小到大排序
            SortedSet<String> sortedSet = new TreeSet<>();
            for(String children:childrenNodes){
                sortedSet.add(ROOT_LOCKS+"/"+children);
            }
            String first = sortedSet.first();
            if(lockId.equals(first)){
                //表示当前是最小的节点
                System.out.println(Thread.currentThread().getName()+"->成功获得锁，lock节点为:["+lockId+"]");
                return true;
            }
            //获取比当前更小的集合
            SortedSet<String> lessThanLock = sortedSet.headSet(lockId);
            if(!lessThanLock.isEmpty()){
                //拿到比当前lockId节点更小的上一个节点
                String prevLockId = lessThanLock.last();
                zooKeeper.exists(prevLockId,new LockWatcher(countDownLatch));
                countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
                //上面这段代码表示如果会话超时，或者节点被删除（释放）了
                System.out.println(Thread.currentThread().getName()+" 成功获取锁:["+lockId+"]");
            }
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    //释放锁
    public boolean unlock(){
        System.out.println(Thread.currentThread().getName()+"->开始释放锁:["+lockId+"]");
        try {
            zooKeeper.delete(lockId,-1);
            System.out.println("节点["+lockId+"]成功被删除");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        Random random = new Random();
        for(int i=0;i<10;i++){
            new Thread(()->{
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock();
                    latch.countDown();
                    latch.await();
                    lock.lock();
                    Thread.sleep(random.nextInt(500));
                }  catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }finally {
                    if(lock!=null){
                        lock.unlock();
                    }
                }
            }).start();
        }
    }


}
