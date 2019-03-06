package com.better517na.concurrence.concurrence01;

/**
 * @author lingbai
 * @create 2019-02-27 19:22
 * @desc 测试守护线程
 **/
public class Thread05 extends Thread {

    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + (i));
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread05 thread05 = new Thread05();
        thread05.setDaemon(true);
        thread05.start();
        Thread.sleep(5000);
        System.out.println("我停止守护线程就停止！！！");
    }
}