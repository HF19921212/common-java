package com.city.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 验证Lock锁的锁抢占、锁重入以及线程加入队列和释放锁
 */
public class AtomicTest {

    private static int count=0;
    private static Lock lock = new ReentrantLock();
    public static void inc(){
        try {
            lock.lock();
            Thread.sleep(1);
            count++;
            decr();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void decr(){
        //抢占锁,锁重入
        lock.lock();
        count--;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 1000; i++ ){
            new Thread(()->AtomicTest.inc()).start();
        }
        Thread.sleep(4000);
        System.out.println("result："+count);
    }
}
