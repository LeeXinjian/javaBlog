package org.example.algorithm.sort;

import java.util.ArrayList;

public abstract class Sort {
    public ArrayList<Integer> doSort(ArrayList<Integer> unSortArray) {
        if (!checkList(unSortArray)) {
            return unSortArray;
        }
        return sort(unSortArray);
    }

    public int[] doSort(int[] unSortArray) {
        if (!checkArray(unSortArray)) {
            return unSortArray;
        }
        return sort(unSortArray);
    }

    public String getSortName(){
        return getName();
    }

    protected abstract ArrayList<Integer> sort(ArrayList<Integer> unSortArray);

    protected abstract int[] sort(int[] unSortArray);

    protected String getName(){
        SortName annotation = this.getClass().getAnnotation(SortName.class);
        if (annotation != null) {
            return annotation.value();
        }
        return null;
    }

    private boolean checkArray(int[] unSortArray) {
        return unSortArray != null && unSortArray.length > 1;
    }

    private boolean checkList(ArrayList<Integer> unSortArray) {
        return unSortArray != null && unSortArray.size() > 1;
    }
}
