package com.jiajinshuo;

/**
 * @author jiajinshuo
 * @create 2020-04-13 15:32
 */
public class SaleTicket01 {
    //线程就是一个单独的资源类，没有任何附属操作，传统的synchronized
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(()-> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        },"a").start();

        new Thread(()-> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        },"b").start();

        new Thread(()-> {
            for (int i = 1; i < 60; i++) {
                ticket.sale();
            }
        },"c").start();
    }

}
/**
 * 没有实现接口，只是纯粹的类
 */
class Ticket{
    private int tickets = 50;
    public synchronized void sale(){
        if(tickets>0){
            System.out.println(Thread.currentThread().getName()+"卖出第"+tickets--+"张票,剩余"+tickets+"张票");
        }
    }
}