package com.jiajinshuo.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author jiajinshuo
 * @create 2020-05-24 21:07
 */
public class ThreadLocalDemo {

    ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private String content;

    public void setContent(String content){
        threadLocal.set(content);
    }
    public String getContent(){
        return threadLocal.get();
    }
}
class ThreadLocalTest{
    public static void main(String[] args) throws InterruptedException {

        ThreadLocalDemo td = new ThreadLocalDemo();

        for (int i = 0; i < 5; i++) {

            new Thread(()->{
                td.setContent(Thread.currentThread().getName()+"的数据");
                System.out.println(Thread.currentThread().getName()+"获得"+td.getContent());

            }).start();

        }

    }
}