package com.jiajinshuo.produce;

/**
 * @author jiajinshuo
 * @create 2020-05-08 15:33
 */
public class A {
    public static void main(String[] args){
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"生产者A").start();
       

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"消费者C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"生产者B").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"消费者D").start();

    }
}
/**
 * 线程之间的通信问题：生产者和消费者问题！等待唤醒，通知唤醒
 * 线程交替执行A B 操作同一个变量num = 0
 * A num+1
 * B num-1
 * 传统的Synchronized
 */

class Data{

    private int num = 0;

    public synchronized void increment()throws Exception{
        while (num != 0){
            // 如果不等于0等待，否则+1
            wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"---->"+num);
        notifyAll();
    }

    public synchronized  void decrement() throws Exception{
        while (num == 0){
            wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"---->"+num);
        notifyAll();
    }

}