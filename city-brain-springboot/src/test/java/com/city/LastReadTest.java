package com.city;

import java.util.Date;

public class LastReadTest {
    public static void main(String[] args) throws InterruptedException {
        Long lastRead = System.currentTimeMillis();
        Thread.sleep(5000);

        Date date = new Date();
        Long now = date.getTime();
        System.out.println(now - lastRead);
    }
}
