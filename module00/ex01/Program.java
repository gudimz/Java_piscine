
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean isPrime = true;

        if (in.hasNextInt()) {
            input = in.nextInt();
            if (input <= 1) {
                System.err.println("Illegal Argument");
                in.close();
                System.exit(-1);
            }
        } else {
            System.err.println("Illegal Argument");
            in.close();
            System.exit(-1);
        }

        int count = 1;
        for (int i = 2; i * i <= input; i++) {
            if (input % i == 0) {
                isPrime = false;
                break;
            }
            count++;
        }

        System.out.println(isPrime + " " + count);
        in.close();
    }
}
