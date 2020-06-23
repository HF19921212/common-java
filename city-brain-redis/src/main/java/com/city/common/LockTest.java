package com.city.common;

import com.city.common.lock.RedisLock;
import com.city.common.util.JedisPoolUtil;
import com.city.common.util.SnowflakeIdWorker;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        //模拟客户端数量
        int clientCount =100;
        //计数器
        AtomicInteger count = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        RedisLock redisLock = new RedisLock();

        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientCount;i++){
            executorService.execute(() -> {
                //通过Snowflake算法获取唯一的ID字符串
                String id = String.valueOf(idWorker.nextId());
                try {
                    redisLock.lock(id);
                    System.out.println("获取锁 id"+id);
                    count.getAndIncrement();
                }finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("执行线程数:{"+clientCount+"},总耗时:{"+(end-start)+"},count数为:{"+count+"}");
        executorService.shutdown();
        //释放redis线程池
        //JedisPoolUtil.release(redisLock.jedisPool,redisLock.jedisPool.getResource());
    }


}
