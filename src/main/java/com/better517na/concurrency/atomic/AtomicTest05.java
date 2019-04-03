package com.better517na.concurrency.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author lingbai
 * @create 2019-03-12 18:16
 * @desc 测试AtomicIntegerFieldUpdater(更新类里面的字段的值)，这个值必须由volatile修饰，不能被static修饰
 **/
@Slf4j
public class AtomicTest05 {

    private static AtomicIntegerFieldUpdater<AtomicTest05> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicTest05.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicTest05 test = new AtomicTest05();
        if (updater.compareAndSet(test, 100, 120)) {
            log.info("update success 1 , {}", test.getCount());
        }
        if (updater.compareAndSet(test, 100, 120)) {
            log.info("update success 2 , {}", test.getCount());
        } else {
            log.info("update failed , {}", test.getCount());
        }
    }

}