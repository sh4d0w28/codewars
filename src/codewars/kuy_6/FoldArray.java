package kuy_6;

import java.util.Arrays;

public class FoldArray {

    public static int[] foldArray(int[] array, int runs)
    {
        for (int i = 0; i< runs; i++) {
            array = foldOnce(array);
        }
        return array;
    }

    public static int[] foldOnce(int[] a) {
        int b = 0;
        int e = a.length - 1;

        int resLen = 0;
        if ( a.length % 2 == 0) {
            resLen = a.length / 2;
        } else {
            resLen = (a.length+1) / 2;
        }
        int[] resArr = new int[resLen];

        int i = 0;
        while (b < e) {
            resArr[i++] = a[b] + a[e];
            b++; e--;
        }

        if (a.length % 2 == 1) {
            resArr[b] = a[b];
        }

        return resArr;
    }

    public static void main(String[] args) {

        System.out.println(3/2);

        int[] input = new int[] { 1, 2, 3, 4, 5 };
        int[] expected = new int[] { 6, 6, 3 };

        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(FoldArray.foldArray(input, 1)));
    }
}
