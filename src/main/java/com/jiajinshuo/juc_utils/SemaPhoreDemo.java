package com.jiajinshuo.juc_utils;

import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author jiajinshuo
 * @create 2020-05-22 19:06
 */
public class SemaPhoreDemo {
    public static void main(String[] args) throws Exception {

        // 设置3个车位，六个线程抢，抢到要-1
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <7 ; i++) {

            new Thread(()->{
                // 抢到车位
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"得到线程");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"释放线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 信号量+1
                    semaphore.release();
                }
            }).start();

        }
    }
}
