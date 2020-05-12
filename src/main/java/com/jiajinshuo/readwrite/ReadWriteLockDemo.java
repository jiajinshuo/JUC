package com.jiajinshuo.readwrite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jiajinshuo
 * @create 2020-05-09 22:28
 * 读写锁
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        /**
         * 写数据
         */
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");

            },String.valueOf(i)).start();

        }
        /**
         * 读数据
         */
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
             },String.valueOf(i)).start();

        }



    }
}
class MyCache{

    private volatile Map<String,Object> hashmap = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            hashmap.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 取数据
     * @param key
     */
    public void get(String key){

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取");
            Object o = hashmap.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}