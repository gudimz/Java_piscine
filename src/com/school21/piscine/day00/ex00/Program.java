package com.school21.piscine.day00.ex00;

public class Program {
    public static void main(String[] args) {
        int digit = 479598;
        if (digit < 0) {
            digit *= -1;
        }
        int result = 0;
        while (digit > 0) {
            result += digit % 10;
            digit /= 10;
        }
        System.out.println(result);
    }
}
