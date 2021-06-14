package org.example.algorithm.bit;

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 */
public class O3_FindRepeatNums {

    public static void main(String[] args) {
        int[] arr = initArray();
        int repeat = findRepeatNum(arr);
        System.out.println(repeat);
    }

    private static int findRepeatNum(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 如果当前位置上的值和当前值不相等
            if (arr[i] != i) {

                // 如果当前值的下标位置处,已经有下标处值=下标 则说明重复了
                if (arr[arr[i]] == arr[i]) {
                    return arr[i];
                }

                int tmp = arr[i];
                arr[i] = arr[tmp];
                arr[tmp] = tmp;
            }
        }

        return -1;
    }


    private static int[] initArray() {

//        0,1,2,3,4,5,6
//        0,4,3,2,5,1,5
        return new int[]{1, 2, 3, 1, 2, 2, 3, 2};
    }
}
