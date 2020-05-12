package com.jiajinshuo;

/**
 * @author jiajinshuo
 * @create 2020-04-13 14:51
 */
public class Test01 {
    public static void main(String[] args) {
        //获取cpu的核数
        //CPU密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}

