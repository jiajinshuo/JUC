package com.jiajinshuo.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jiajinshuo
 * @create 2020-05-09 18:30
 */
public class CopyOnWriteTest {
    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();

        /**
         * 使用原生的集合会报错java.util.ConcurrentModificationException
         * 解决方法：
         *  1，Vector<Object> objects = new Vector<>();
         *  2，List<String> list = Collections.synchronizedList(new ArrayList<>());
         *  3,List<String> list = new CopyOnWriteArrayList<>();
         */
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            }).start();
        }
    }
}
