package com.liusxg.patterns.singleton;

/**
 * Created by liusxg on 2017/10/15.
 *
 * 单例模式-懒汉式-double-check-ThreadLocal版本
 *
 */
public class Singleton5 {

    private static final ThreadLocal perThreadInstance = new ThreadLocal();

    private static Singleton5 singleton5 = null;

    /**
     * 私有化构造器
     */
    private Singleton5() {}

    /**
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 懒汉式，只有需要的时候才会去生成实例
     *
     * 特点，双重锁检查, 使用ThreadLocal修复double-check
     *
     * @return
     */
    public static Singleton5 getInstance() {
        if (perThreadInstance.get() == null) {
            // 每个线程第一次都会调用
            createInstance();
        }
        return singleton5;
    }

    private static final void createInstance() {
        synchronized (Singleton5.class) {
            if (singleton5 == null) {
                singleton5 = new Singleton5();
            }
        }
        perThreadInstance.set(perThreadInstance);
    }

    /**
     * 实例方法, 只有创建了实例才能调用
     * @return
     */
    public String getName() {
        return "单例模式-懒汉式-double-check-ThreadLocal版本";
    }

}
