package module03.ex02;

import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    public static long sumFromArray = 0;
    public static long sumFromThreads = 0;

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.err.println("Bad argument");
            System.exit(1);
        }

        int arraySize = 0;
        int threadsCount = 0;
        try {
            arraySize = Integer.parseInt(args[0].substring(12));
            threadsCount = Integer.parseInt(args[1].substring(15));
            if (arraySize < threadsCount) {
                System.err.println("Error: the count of threads is greater than size of the array");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: incorrect argument");
        }

        int[] arrayNumbers = new int[arraySize];
        for (int i = 0; i < arrayNumbers.length; i++) {
            int number = (int)(Math.random() * 1000);
            sumFromArray += number;
            arrayNumbers[i] = number;
        }
        System.out.println("Sum: " + sumFromArray);

        int offset = arraySize / threadsCount;
        int remainder = arraySize % threadsCount;
        ArrayList<Thread> listThreads = new ArrayList<>(threadsCount);
        int start = 0;
        int end = 0;
        int num = 1;

        while (end < arraySize - offset - remainder) {
            end = start + offset;
            listThreads.add(new Thread(new Calculator(num, Arrays.copyOfRange(arrayNumbers, start, end), start, end)));
            start = end;
            num++;
        }
        end = start + offset + remainder;
        listThreads.add(new Thread(new Calculator(num, Arrays.copyOfRange(arrayNumbers, start, end), start, end)));
        for (Thread thread : listThreads) {
            thread.start();
        }

        for (Thread thread : listThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads: " + sumFromThreads);
    }
}
