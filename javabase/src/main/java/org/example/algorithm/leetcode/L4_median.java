package org.example.algorithm.leetcode;

import java.math.BigDecimal;

public class L4_median {


    public static void main(String[] args) {
        int[] num1 = {1,2,2,4};
        int[] num2 = {1,3,4,5,6,7};
//        System.out.println(new L4_median().findMedianSortedArrays(num1,num2));
    }
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        // 长度
//        int allLength = nums1.length + nums2.length;
//
//        // 偶数
//        if (allLength % 2 == 0) {
//            return even(nums1, nums2, allLength / 2 - 1);c
//        } else {
//            return odd(nums1, nums2, allLength % 2 + 1);
//        }
//    }
//
//    private double even(int[] nums1, int[] nums2, int indexLeft) {
//        int i = 0;
//        int j = 0;
//
//        int k = 0;
//
//        int indexLeftValue = 0;
//        int indeRightValue = 0;
//
//        while (k <= indexLeft) {
//            while (nums1[i] <= nums2[j] && k < indexLeft && i < nums1.length) {
//                i++;
//                k++;
//            }
//
//            while (nums1[j] <= nums2[i] && k < indexLeft && j < nums2.length) {
//                j++;
//                k++;
//            }
//        }
//
//        indexLeftValue = Math.min(nums1[i],nums2[j]);
//
//        if (nums1[i+1] < nums2[j]) {
//            indeRightValue = nums1[i+1];
//        }
//
//        if (nums1[j+1] < nums2[i]) {
//            indeRightValue = nums1[j+1];
//        }
//
//        return new BigDecimal(indeRightValue + indexLeftValue).divide(new BigDecimal(2)).doubleValue();
//    }
//
//    private double odd(int[] nums1, int[] nums2, int i) {
//        return 0;
//    }

}
