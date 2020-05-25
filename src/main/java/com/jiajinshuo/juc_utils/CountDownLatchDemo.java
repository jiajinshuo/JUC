package com.jiajinshuo.juc_utils;

import java.util.concurrent.CountDownLatch;

/**
 * @author jiajinshuo
 * @create 2020-05-22 17:39
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 倒计时多少这里就填多少
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i < 7; i++) {

            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "离开教室");
                // 减一
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }

        // 班长卡在这里不能走，阻塞。直到减到0
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长走人");


    }


}
