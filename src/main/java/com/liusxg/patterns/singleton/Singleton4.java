package com.liusxg.patterns.singleton;

/**
 * Created by liusxg on 2017/10/15.
 *
 * 单例模式-懒汉式-double-check-volatile版本
 *
 */
public class Singleton4 {

    private volatile static Singleton4 singleton4 = null;

    /**
     * 私有化构造器
     */
    private Singleton4() {}

    /**
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 懒汉式，只有需要的时候才会去生成实例
     *
     * 特点，双重锁检查, 使用volatile关键字来保证singleton4在对象初始化完成后修改值立马可见
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (singleton4 == null) {
                synchronized (Singleton4.class) {
                    if (singleton4 == null) {
                        singleton4 = new Singleton4();
                    }
                }
        }
        return singleton4;
    }

    /**
     * 实例方法, 只有创建了实例才能调用
     * @return
     */
    public String getName() {
        return "单例模式-懒汉式-double-check-volatile版本-singleton4";
    }

}
