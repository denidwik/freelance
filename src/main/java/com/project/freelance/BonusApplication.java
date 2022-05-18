package com.project.freelance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BonusApplication {
    public static void main(String[] args) {
        BonusApplication bonusApplication = new BonusApplication();
        int maxNumber = 6;
        int[] arrayNumberDependOnMaxNumber = new int[]{0, 2, 0, 4, 0, 0};
        List<int[]> a = bonusApplication.getPermutations(arrayNumberDependOnMaxNumber);
        for (int[] b : a) {
            System.out.println(Arrays.toString(b));
        }
    }

    public List<int[]> getPermutations(int[] array){
        List<int[]> result = new ArrayList<>();
        helper(array, 0, result);
        return result;
    }

    private void count(int[] array, List<int[]> result) {
        helper(array, 0, result);
    }

    private void helper(int[] array, int pos, List<int[]> result) {
        if(pos >= array.length - 1) {
            int[] arrayResult = new int[array.length];
            int counter = 0;
            for(int i = 0; i < array.length - 1; i++){
                arrayResult[counter] = array[i];
                counter ++;
            }
            if(array.length > 0)
                arrayResult[counter] = array[array.length - 1];
            if (!contains(result, arrayResult))
                result.add(arrayResult);
            return;
        }

        for(int i = pos; i < array.length; i++){
            int t = array[pos];
            array[pos] = array[i];
            array[i] = t;

            helper(array, pos+1, result);

            t = array[pos];
            array[pos] = array[i];
            array[i] = t;
        }
    }

    private boolean contains(List<int[]> list, int[] array) {
        return list.stream()
                .anyMatch(x -> Arrays.equals(x, array));
    }
}
