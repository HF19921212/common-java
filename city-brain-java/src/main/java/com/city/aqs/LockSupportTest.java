package com.city.aqs;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("T1"){
            @Override
            public void run() {
                System.out.println("T1 被执行！");
                System.out.println("T1 被阻塞！");
                LockSupport.park();
                System.out.println("T1 被唤醒！");
            }
        };

        t1.start();
        Thread.sleep(5000);
        LockSupport.unpark(t1);
    }

}
