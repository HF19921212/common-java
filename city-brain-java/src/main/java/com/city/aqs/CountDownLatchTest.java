package com.city.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    static int sum = 20;
    public static void main(String[] args) throws InterruptedException {
        CityBrainLock cityBrainLock = new CityBrainLock();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        int count = 20;
        for(int i = 0;i < count;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cityBrainLock.lock();
                        if(sum > 0){
                            Thread.sleep(1000);
                            sum --;
                            System.out.println(Thread.currentThread().getId()+"：购买了一个库存："+sum);
                        }else{
                            System.out.println("卖完啦");
                        }
                        cityBrainLock.unlock();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("我被阻塞了");
        countDownLatch.await();
        System.out.println("我被释放了");
        System.out.println("剩余："+sum);
    }
}
