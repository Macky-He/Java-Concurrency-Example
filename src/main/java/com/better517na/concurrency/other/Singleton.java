package com.better517na.concurrency.other;

/**
 * @author lingbai
 * @create 2019-03-05 14:37
 * @desc 双重校验锁实现单例模式
 *
 * uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
 *
 * 1.为 uniqueInstance 分配内存空间
 * 2.初始化 uniqueInstance
 * 3.将 uniqueInstance 指向分配的内存地址
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出先问题，
 * 但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，
 * 此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，
 * 但此时 uniqueInstance 还未被初始化。
 *
 * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 **/
public class Singleton {

    /**
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     */
    private volatile static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        //首先判断对象是否实例化
        if (instance == null){
            //给类对象加锁
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}