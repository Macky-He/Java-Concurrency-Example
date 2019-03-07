package com.better517na.concurrency.concurrency01;

/**
 * @author lingbai
 * @create 2019-02-27 19:16
 * @desc 测试线程优先级
 **/
public class Thread04 extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("Thread04的优先级为：" + this.getPriority());
    }

    public static void main(String[] args) {
        Thread03 thread03 = new Thread03();
        Thread04 thread04 = new Thread04();
        thread04.setPriority(5);
        thread03.setPriority(8);
        thread04.start();
        thread03.start();
    }
}