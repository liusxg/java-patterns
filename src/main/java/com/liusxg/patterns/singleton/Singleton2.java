package com.liusxg.patterns.singleton;

/**
 * Created by liusxg on 2017/10/15.
 *
 * 单例模式-饿汉式
 */
public class Singleton2 {

    private static Singleton2 singleton2 = new Singleton2();

    /**
     * 私有化构造器
     */
    private Singleton2() {}

    /**
     * 这是最简单的单例模式
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 饿汉式，优先生成实例
     *
     * @return
     */
    public static Singleton2 getInstance() {
        return singleton2;
    }

    /**
     * 实例方法, 只有创建了实例才能调用
     * @return
     */
    public String getName() {
        return "最简单的单例模式-饿汉式-singleton2";
    }

}
