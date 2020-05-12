package com.jiajinshuo.condition_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiajinshuo
 * @create 2020-05-09 10:49
 */
public class Condition_Lock {
    public static void main(String[] args) {

        ConditionA conditionA = new ConditionA();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                conditionA.PrintA();

            }

        },"线程A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                conditionA.PrintB();

            }

        },"线程B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                conditionA.PrintC();

            }

        },"线程C").start();

    }
}

/**
 * condition精准唤醒
 * A1---->B2----->C3---->A1
 */
class ConditionA{

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int num = 1;

    public void PrintA(){
        lock.lock();
        try {
            while (num != 1){
                condition1.await();
            }
            num = 2;
            System.out.println(Thread.currentThread().getName()+"----->"+"AAAA");
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void PrintB(){
        lock.lock();
        try {
            while (num != 2){
                condition2.await();
            }
            num = 3;
            System.out.println(Thread.currentThread().getName()+"----->"+"BBBB");
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void PrintC(){
        lock.lock();
        try {
            while (num != 3){
                condition3.await();
            }
            num = 1;
            System.out.println(Thread.currentThread().getName()+"----->"+"CCCC");
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}