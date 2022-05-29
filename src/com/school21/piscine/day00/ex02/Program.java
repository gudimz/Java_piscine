package com.school21.piscine.day00.ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int inputNum = 0;
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            inputNum = in.nextInt();
        }

        int countRequest = 0;
        while (inputNum != 42) {
            if (isPrime(sum(inputNum))) {
                countRequest += 1;
            }
            inputNum = in.nextInt();
        }

        in.close();
        System.out.println("Count of coffee-request – " + countRequest);
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int sum(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}
