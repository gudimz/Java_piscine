
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

        int[] Numbers = new int[arraySize];
        for (int i = 0; i < Numbers.length; i++) {
            int number = (int)(Math.random() * 1000);
            sumFromArray += number;
            Numbers[i] = number;
        }
        System.out.println("Sum: " + sumFromArray);

        int remainder = arraySize % threadsCount;
        int offset;
        if (remainder != 0) {
            offset = arraySize / threadsCount + 1;
        } else {
            offset = arraySize / threadsCount;
        }
        ArrayList<Thread> Threads = new ArrayList<>(threadsCount);
        int num = 1;

        for (int i = 0; i < threadsCount - 1; i++) {
            int start = i * offset;
            int end = (i + 1) * offset;
            Threads.add(new Thread(new Calculator(num, Arrays.copyOfRange(Numbers, start, end), start, end - 1)));
            num++;
        }
        int start = (threadsCount - 1) * offset;
        Threads.add(new Thread(new Calculator(num, Arrays.copyOfRange(Numbers, start, Numbers.length), start, Numbers.length)));
        for (Thread thread : Threads) {
            thread.start();
        }

        for (Thread thread : Threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads: " + sumFromThreads);
    }
}
