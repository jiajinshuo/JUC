package com.jiajinshuo.readwrite;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author jiajinshuo
 * @create 2020-05-10 18:15
 * 测试线程的优先级：即使B在后面，但还是有机会先执行
 */
public class ThreadTest {
    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 10; i++) {

                System.out.println("A");
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {

                System.out.println("C");
            }
        }).start();

//        System.out.println("CC");

    }

}

