package com.jiajinshuo.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author jiajinshuo
 * @create 2020-05-24 21:43
 */
public class SynDemo {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
class SynDemoTest{
    public static void main(String[] args) throws InterruptedException {

        SynDemo synDemo = new SynDemo();

        for (int i = 0; i < 5; i++) {

                new Thread(()->{

                    synchronized (SynDemo.class){
                        synDemo.setContent(Thread.currentThread().getName()+"的数据");
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"获得"+synDemo.getContent());
                    }

                }).start();


    }


//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(synDemo.getContent());

    }
}