package com.better517na.concurrency.other;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lingbai
 * @create 2019-03-07 11:49
 * @desc 测试Demo类
 **/
public class Demo {

    public void testAtomic() {

        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.compareAndSet(1, 2);
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 5, 6, 7, 8, 10, 11, 13, 15, 49};
        int i = binSearch(ints, 10);
        System.out.println(i);
    }

    public static int binSearch(int[] array, int a) {
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end) {
            //中间位置
            mid = (start + end) / 2;
            if (array[mid] == a) {
                return mid + 1;
                //向右查找
            } else if (array[mid] < a) {
                start = mid + 1;
            } else { //向左查找
                start = mid - 1;
            }
        }
        return -1;
    }

}