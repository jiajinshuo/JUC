package com.jiajinshuo.juc_utils;

/**
 * @author jiajinshuo
 * @create 2020-05-22 21:05
 */
public class User {
    private String name;
    private Integer age;

}
class Test{
    public static void main(String[] args) throws ClassNotFoundException {
        Class<User> aClass = (Class<User>) Class.forName("com.jiajinshuo.juc_utils.User");
        Class<User> aClass1 = (Class<User>) Class.forName("com.jiajinshuo.juc_utils.User");
        System.out.println(aClass == aClass1); // true
    }
}
