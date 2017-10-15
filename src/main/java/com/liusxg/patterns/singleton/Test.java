package com.liusxg.patterns.singleton;

/**
 * 单例模式测试类
 *
 */
public class Test {
    public static void main(String[] args) {

        //最简单的单例模式
        Singleton1 singleton1 = Singleton1.getInstance();
        System.out.println(singleton1.getName());
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton2.getName());
        Singleton3 singleton3 = Singleton3.getInstance();
        System.out.println(singleton3.getName());
        Singleton4 singleton4 = Singleton4.getInstance();
        System.out.println(singleton4.getName());
        Singleton5 singleton5 = Singleton5.getInstance();
        System.out.println(singleton5.getName());
        Singleton6 singleton6  = Singleton6.getInstance();
        System.out.println(singleton6.getName());
    }
}
