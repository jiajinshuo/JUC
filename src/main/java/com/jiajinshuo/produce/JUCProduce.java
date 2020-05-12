package com.jiajinshuo.produce;

import javafx.beans.binding.When;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiajinshuo
 * @create 2020-05-08 21:25
 */
public class JUCProduce {
    public static void main(String[] args) {

        ProCus proCus = new ProCus();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    proCus.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"生产者A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    proCus.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"生产者C").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    proCus.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"消费者B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    proCus.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"消费者D").start();

    }
}
class ProCus{

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private int num = 0;

    /**
     * 生产者+1
     * @throws Exception
     */
    public void increase()throws Exception{

        lock.lock();
        try {
            while (num != 0){
                // 等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"------>"+num);
            // 唤醒全部
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrease()throws Exception{
        lock.lock();
        try {
            while (num == 0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"------>"+num);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}