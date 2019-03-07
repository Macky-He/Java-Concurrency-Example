package com.better517na.concurrency.concurrency01;

/**
 * @author lingbai
 * @create 2019-02-27 19:05
 * @desc 停止一个线程,interrupt不会中止一个线程，return会中止线程
 **/
public class Thread02 extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50000; i++) {
            if (this.isInterrupted()){
                System.out.println("线程已经是停止状态了！！！");
                return;
            }
            System.out.println("i = " + (i + 1));
        }
        System.out.println("线程还未停止");
    }

    public static void main(String[] args) {
        try {
            Thread02 thread02 = new Thread02();
            thread02.start();
            Thread.sleep(20);
            thread02.interrupt();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}