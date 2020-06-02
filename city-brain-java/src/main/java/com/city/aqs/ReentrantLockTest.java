package com.city.aqs;

public class ReentrantLockTest {

    static int sum = 10;
    public static void main(String[] args) throws InterruptedException {
        CityBrainLock cityBrainLock = new CityBrainLock();
        int count = 20;
        for(int i = 0;i < count;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取锁
                        cityBrainLock.lock();
                        if(sum > 0){
                            Thread.sleep(100);
                            sum --;
                            System.out.println(Thread.currentThread().getId()+"：购买了一个库存："+sum);
                        }else{
                            System.out.println("卖完啦");
                        }
                        //释放锁
                        cityBrainLock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(sum);
    }

}
