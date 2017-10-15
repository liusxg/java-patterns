package com.liusxg.patterns.singleton;

/**
 * Created by liusxg on 2017/10/15.
 *
 * 单例模式-懒汉式-保证线程安全-synchronized同步版本
 *
 */
public class Singleton3 {

    private static Singleton3 singleton3 = null;

    /**
     * 私有化构造器
     */
    private Singleton3() {}

    /**
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 饿汉式，优先生成实例
     *
     * 特点，每次调用getInstance方法都要获取Singleton3.class的锁。
     *
     * @return
     */
    public synchronized static Singleton3 getInstance() {
        if (singleton3 == null) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }

    /**
     * 实例方法, 只有创建了实例才能调用
     * @return
     */
    public String getName() {
        return "单例模式-饿汉式-保证线程安全-synchronized同步版本-singleton3";
    }

}
