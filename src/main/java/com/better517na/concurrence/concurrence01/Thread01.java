package com.better517na.concurrence.concurrence01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingbai
 * @create 2019-02-27 18:47
 * @desc 多线程之间不共享变量线程安全情况
 **/
public class Thread01 extends Thread{

    /**
     * 使用volatile关键字实现线程安全
     */
//    private volatile int count = 5;

    /**
     * 普通变量
     */
    private AtomicInteger count = new AtomicInteger(5);

    public Thread01(String name){
        super();
        this.setName(name);
    }
//    @Override
//    public void run() {
//        super.run();
//        count--;
//        System.out.println("由线程：" + Thread01.currentThread().getName() + "计算，count=" + count);
//    }

    @Override
    public void run() {
        super.run();
//        while (count > 0) {
            count.getAndDecrement();
            System.out.println("由线程：" + Thread01.currentThread().getName() + "计算，count=" + count);
//        }
    }
        public static void main(String[] args) {
        Thread01 a = new Thread01("A");
        Thread01 b = new Thread01("B");
        Thread01 c = new Thread01("C");
        Thread01 d = new Thread01("D");
        a.start();
        b.start();
        c.start();
        d.start();
        System.out.println("运行结束！！！");
    }

//    public static void main(String[] args) {
//        Thread01 thread01 = new Thread01();
//        Thread a = new Thread(thread01,"A");
//        Thread b = new Thread(thread01,"B");
//        Thread c = new Thread(thread01,"C");
//        Thread d = new Thread(thread01,"D");
//        a.start();
//        b.start();
//        c.start();
//        d.start();
//        System.out.println("运行结束！！！");
//    }
}