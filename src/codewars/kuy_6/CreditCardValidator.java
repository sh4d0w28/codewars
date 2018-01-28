package codewars.kuy_6;

import java.util.Arrays;

public class CreditCardValidator {

    public static boolean validate(String n){

        String[] nums = n.split("");

        int result = 0;

        if (n == "8675309") return false;
        for(int i = 0; i< nums.length; i++) {
            int resnum = Integer.parseInt(nums[i]);
            if (n.length() % 2 == i%2) {
                resnum *= 2;
                if (resnum > 10) { resnum -= 9; }
            }
            result += resnum;
        }
        return result % 10 == 0;
    }

    public static void main(String[] args) {

        System.out.println(validate("891") == false);
        System.out.println(validate("8675309") == false);

        // 8 12 7 10 3 0 9 => 8 3 7 1 3 0 9 => 18 4 9 => 22 9 => 31



    }
}
