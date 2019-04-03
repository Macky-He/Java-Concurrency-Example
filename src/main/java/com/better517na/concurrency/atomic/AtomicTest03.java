package com.better517na.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author lingbai
 * @create 2019-03-12 18:16
 * @desc 测试LongAdder(将热点数据分离，较之AtomicLong有着效率更加高，更加安全)
 * 缺点：统计时可能导致数据出错，全局统计最好使用AtomicLong
 **/
@Slf4j
public class AtomicTest03 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 并发执行线程总数
     */
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e){
                    log.info(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    public static void add(){
        count.increment();
    }
}