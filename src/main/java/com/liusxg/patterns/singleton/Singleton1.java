package com.liusxg.patterns.singleton;

/**
 * Created by liusxg on 2017/10/15.
 *
 * 单例模式-懒汉式
 *
 */
public class Singleton1 {

    private static Singleton1 singleton1 = null;

    /**
     * 私有化构造器
     */
    private Singleton1() {}

    /**
     * 这是最简单的单例模式
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 懒汉式，只有需要的时候才会去生成实例
     *
     * @return
     */
    public static Singleton1 getInstance() {
        if (singleton1 == null) {
            singleton1 = new Singleton1();
        }
        return singleton1;
    }

    /**
     * 实例方法, 只有创建了实例才能调用
     * @return
     */
    public String getName() {
        return "最简单的单例模式-懒汉式-singleton1";
    }

}
