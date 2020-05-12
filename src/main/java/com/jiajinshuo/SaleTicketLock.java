package com.jiajinshuo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiajinshuo
 * @create 2020-04-13 16:40
 */
public class SaleTicketLock{
    //线程就是一个单独的资源类，没有任何附属操作，传统的synchronized
    public static void main(String[] args) {

        Ticket02 ticket02 = new Ticket02();
        new Thread(()-> {
            for (int i = 1; i < 60; i++) {
                ticket02.sale();
            }
        },"a").start();

        new Thread(()-> {
            for (int i = 1; i < 60; i++) {
                ticket02.sale();
            }
        },"b").start();

        new Thread(()-> {
            for (int i = 1; i < 60; i++) {
                ticket02.sale();
            }
        },"c").start();
    }

}

class Ticket02{
    private int tickets = 50;
    Lock lock = new ReentrantLock();

    public synchronized void sale(){
        //加锁
        lock.lock();

        try {
            //业务逻辑在try catch里面
            if(tickets>0){
                System.out.println(Thread.currentThread().getName()+"卖出第"+tickets--+"张票,剩余"+tickets+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
