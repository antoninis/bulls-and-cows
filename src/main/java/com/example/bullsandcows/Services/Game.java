package com.example.bullsandcows.Services;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Scanner;
public class Game {

    public static String computerNumber;
    public static String userNumber;

    public static void setNumber() {
        String number = "";
        int length = 4;
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < length; i++) {
            while (true) {
                int x = (int) (Math.random() * 10);
                if (nums[x] == x) {
                    number = number + x;
                    nums[x] = -1;     //исключение возможностиобратиться к данному числу
                    break;
                }
            }
        }
        System.out.println(number);
        computerNumber = number;
    }

    public static String countBullsAndCows(String userNumber){
        int bulls = 0;
        int cows = 0;
        try {
            for (int i = 0; i < userNumber.length(); i++) {
                if (computerNumber.charAt(i) == userNumber.charAt(i)) bulls++;
                if ((userNumber.charAt(i) != computerNumber.charAt(i)) &&
                        ((userNumber.charAt(i) == computerNumber.charAt(0)) ||
                                (userNumber.charAt(i) == computerNumber.charAt(1)) ||
                                (userNumber.charAt(i) == computerNumber.charAt(2)) ||
                                (userNumber.charAt(i) == computerNumber.charAt(3)))) cows++;
            }
        } catch (StringIndexOutOfBoundsException e){
            return "Введено неправильное количество цифр";
        }
        if (userNumber.length()!=4 ) return "Введено неправильное количество цифр";
        if (bulls == 4)  return "4 bulls";
        return bulls + " bulls and " + cows + " cows";
    }
}
