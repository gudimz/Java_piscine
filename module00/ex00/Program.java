package module00.ex00;

public class Program {
    public static void main(String[] args) {
        int digit = 479598;
        int result = 0;

        result += digit % 10;
        digit /= 10;
        result += digit % 10;
        digit /= 10;
        result += digit % 10;
        digit /= 10;
        result += digit % 10;
        digit /= 10;
        result += digit % 10;
        digit /= 10;
        result += digit % 10;

        System.out.println(result);
    }
}
