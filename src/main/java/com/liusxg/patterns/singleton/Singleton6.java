package com.liusxg.patterns.singleton;

/**
 * Created by liusxg on 2017/10/15.
 *
 * 单例模式-Initialization-on-demand holder
 *
 */
public class Singleton6 {

    /**
     * 私有化构造器
     */
    private Singleton6() {}

    /**
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 特点，通过类加载机制来保证生成实例时线程安全，当getInstance要被执行的时候，内部类LazyHolder才会被加载和初始化
     *
     * @return
     */
    public static Singleton6 getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        static final Singleton6 INSTANCE = new Singleton6();
    }

    /**
     * 实例方法, 只有创建了实例才能调用
     * @return
     */
    public String getName() {
        return "单例模式-Initialization-on-demand holder版本-singleton6";
    }

}
