package com.school21.piscine.day00.ex03;

import java.util.Scanner;

public class Program {
    private static final int MAX_WEEKS_COUNT = 18;
    private static final int MAX_GRADES_COUNT = 5;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String firstWord;
        int numWeek;
        long result = 0;

        for (int count = 1; count <= MAX_WEEKS_COUNT; count++) {
            firstWord = getFirstWord(scan);
            if (firstWord.equals(("42")))
                break;

            numWeek = scan.nextInt();
            if (numWeek != count) {
                scan.close();
                exitError();
            }

            result = result * 10 + getMinGrade(scan);
        }

        result = reverseResult(result);
        printProgress(result);
    }

    public static void exitError() {
        System.err.println("Illegal Argument");
        System.exit(-1);
    }

    public static String getFirstWord(Scanner scan) {
        String word = scan.next();
        if (!word.equals("Week") && !word.equals("42")) {
            scan.close();
            exitError();
        }
        return word;
    }

    public static int getMinGrade(Scanner scan) {
        int min = 9;
        int current;

        for (int i = 0; i < MAX_GRADES_COUNT; i++) {
            current = scan.nextInt();
            if (current < 1 || current > 9) {
                scan.close();
                exitError();
            }
            min = (min > current) ? current : min;
        }
        return min;
    }

    public static void printProgress(long result) {
        int weekNum = 1;
        long progress;
        while (result > 0) {
            progress = result % 10;
            System.out.print("Week ");
            System.out.print(weekNum);
            System.out.print(" ");
            while (progress > 0) {
                System.out.print("=");
                progress -= 1;
            }
            System.out.println(">");
            result /= 10;
            weekNum += 1;
        }
    }

    public static long reverseResult(long number) {
        long reverse = 0;
        for (; number > 0; number = number / 10) {
            long remainder = number % 10;
            reverse = reverse * 10 + remainder;
        }
        return reverse;
    }
}