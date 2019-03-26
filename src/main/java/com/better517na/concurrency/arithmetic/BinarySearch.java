package com.better517na.concurrency.arithmetic;

/**
 * @author lingbai
 * @create 2019-03-21 11:00
 * @desc 二分查询算法
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] ints = {1, 3, 5, 6, 7, 9, 11, 13, 15, 20, 54, 69, 82, 88, 90};
        System.out.println(binsearch(ints, 20));
        System.out.println(recursionBinarySearch(ints,20,0,ints.length-1));
    }

    /**
     * 二分查找法（while循环）
     *
     * @param arrys
     * @param target
     * @return 返回数组索引
     */
    public static int binsearch(int[] arrys, int target) {
        //初始化局部变量
        int left = 0;
        int right = arrys.length - 1;
        int mid;
        while (left <= right) {
            // 为了提高效率和防溢出
            mid = mean(left, right);
            //如果中间元素大于要查找元素，则在中间元素的左侧去找正确元素，最右侧变为mid - 1
            if (target < arrys[mid]) {
                right = mid - 1;
                //如果中间元素小于要查找元素，则在中间元素的右侧去找正确元素，最左侧变为mid + 1
            } else if (target > arrys[mid]) {
                left = mid + 1;
            } else {
                //返回索引
                return mid;
            }
        }
        // 当查找范围的最左侧和最右侧重叠后还没有找到元素，则返回-1表示没有找到
        return -1;
    }

    /**
     * java安全防溢出的两整数平均值算法
     *
     * @param x
     * @param y
     * @return
     */
    public static int mean(int x, int y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    /**
     * 使用递归的二分查找
     *
     * @param arr 有序数组
     * @param key 待查找关键字
     * @return 数组索引
     */
    public static int recursionBinarySearch(int[] arr, int key, int left, int right) {
        //数据判断
        if (key < arr[left] || key > arr[right] || left > right) {
            return -1;
        }
        //初始中间位置
        int middle = mean(left,right);
        if (arr[middle] > key) {
            //比关键字大则关键字在左区域
            return recursionBinarySearch(arr, key, left, middle - 1);
        } else if (arr[middle] < key) {
            //比关键字小则关键字在右区域
            return recursionBinarySearch(arr, key, middle + 1, right);
        } else {
            return middle;
        }

    }
}