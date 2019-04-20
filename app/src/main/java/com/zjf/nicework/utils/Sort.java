package com.zjf.nicework.utils;

/**
 * @author jingfeng
 * @date 2019-04-20 11:07
 * @email 15919820315@163.com
 */
public class Sort {
    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int temp = 0;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序 //https://blog.csdn.net/shujuelin/article/details/82423852
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp是基准点
        temp = arr[low];
        while (i < j) {
            //先看右边，依次往左递减
            while (temp < arr[j] && i < j) {
                j--;
            }
            while (temp > arr[i] && i < j) {
                i++;
            }
            //满足条件则交换
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        //最后将基准为i和j相等位置的数交换
        arr[low] = arr[i];
        arr[i] = temp;

        //最后在递归调用左半数组 和 右半数组
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    /**
     * 排好序的数组使用二分查找法
     * @param arr
     * @param x
     * @return
     */
    public static int binSearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (x == arr[middle]) {
                return middle;
            } else if (x < arr[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}