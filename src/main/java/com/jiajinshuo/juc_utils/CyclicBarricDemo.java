package com.jiajinshuo.juc_utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jiajinshuo
 * @create 2020-05-22 17:51
 */

public class CyclicBarricDemo {
    /**
     * 数到多少才开始。人到齐再开会
     */
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 1 ; i <= 7; i++) {

            final int tempI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"第"+tempI+"颗龙珠");
                try {
                    // 人没来齐只能等着
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
