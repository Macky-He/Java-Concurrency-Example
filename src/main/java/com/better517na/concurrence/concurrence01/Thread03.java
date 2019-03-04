package com.better517na.concurrence.concurrence01;

/**
 * @author lingbai
 * @create 2019-02-27 19:14
 * @desc 测试线程优先级
 **/
public class Thread03 extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("Thread03的优先级为：" + this.getPriority());
        Thread04 thread04 = new Thread04();
        thread04.start();
    }
}