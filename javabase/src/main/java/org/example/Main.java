package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] b = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(
                new Main().findLengthDP(a, b)
        );
    }

    public int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0;
        }

        int[] shortNums;
        int[] longNums;
        if (nums1.length == nums2.length) {
            shortNums = nums1;
            longNums = nums2;
        } else {
            shortNums = nums1.length > nums2.length ? nums2 : nums1;
            longNums = nums1.length > nums2.length ? nums1 : nums2;
        }

        int result = 0;
        int i = 0;

        List<Integer> longList = Arrays.stream(longNums)
                .boxed()
                .collect(Collectors.toList());

        while (i < shortNums.length) {
            // 如果存在当前元素
            ArrayList<Integer> sameElementIndexs = getSameElementIndexs(longList, shortNums[i]);
            if (sameElementIndexs.size() == 0) {
                i++;
                continue;
            }

            int maxi = i;
            for (Integer sameElementIndex : sameElementIndexs) {
                // 公共串长度
                int commonLength = calCommonLength(shortNums, longNums, i, sameElementIndex);
//                maxi = Math.max(maxi, i + commonLength);
                result = Math.max(commonLength, result);
            }
            i++;
//            i = maxi;
        }

        return result;
    }

    /**
     *
     */
    private ArrayList<Integer> getSameElementIndexs(List<Integer> longList, int shortNum) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < longList.size(); i++) {
            if (shortNum == longList.get(i)) {
                resultList.add(i);
            }
        }
        return resultList;
    }


    public int calCommonLength(int[] shortArray, int[] longArray, int shortIndex, int longIndex) {
        int length = 0;
        // 遍历到第一个不等的地方
        while (shortIndex < shortArray.length
                && longIndex < longArray.length
                && shortArray[shortIndex] == longArray[longIndex]) {
            length++;
            shortIndex++;
            longIndex++;
        }
        // 返回相等的长度
        return length;
    }


    public int findLengthDP(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0;
        }

        // dp[i][j] 表示从0-i,0-j 相等的长度
        int[][] dp = new int[nums1.length][nums2.length];
        int result = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        result = Math.max(dp[i][j], result);
                    }
                }
            }
        }

        return result;
    }

}
