
import java.util.Scanner;

public class Program {
    private static int pos = 0;
    private static int countUniqSymbols = 0;
    private static final int MAX_HEIGHT = 10;

    public static boolean isUniq(char[] symbols, char c) {
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == c) {
                pos = i;
                return false;
            }
        }
        return true;
    }

    public static void sort(char[] symbols, int[] counters) {
        for (int i = 0; i < countUniqSymbols - 1; i++) {
            for (int j = countUniqSymbols - 1; j > i; j--) {
                if (counters[j - 1] < counters[j]) {
                    int tmpCount = counters[j - 1];
                    counters[j - 1] = counters[j];
                    counters[j] = tmpCount;
                    char tmpSymbol = symbols[j - 1];
                    symbols[j - 1] = symbols[j];
                    symbols[j] = tmpSymbol;
                } else if (counters[j - 1] == counters[j]) {
                    if (symbols[j - 1] > symbols[j]) {
                        char tmpSymbol = symbols[j - 1];
                        symbols[j - 1] = symbols[j];
                        symbols[j] = tmpSymbol;
                    }
                }
            }
        }
    }

    public static void printHistogram(char[] symbols, int[] counters) {

        int[] lineWithCount = new int[counters.length];
        int max = (counters[0] < MAX_HEIGHT) ? MAX_HEIGHT : counters[0];
        for (int i = 0; i < counters.length; i++) {
            lineWithCount[i] = (counters[i] * MAX_HEIGHT) / max;
        }

        int width = 0;
        int currentLine = (counters[0] < MAX_HEIGHT) ? counters[0] : MAX_HEIGHT;
        for (; currentLine >= 0 ; currentLine--) {
            if (width < lineWithCount.length && currentLine == lineWithCount[width]) {
                for (int j = 0; j < width; j++) {
                    System.out.printf("%3c", '#');
                }
                while(width < lineWithCount.length && lineWithCount[width] == currentLine) {

                    System.out.printf("%3d", counters[width]);
                    width++;
                }
            } else {
                for (int j = 0; j < width; j++) {
                    System.out.printf("%3c", '#');
                }
            }
            System.out.println();
        }

        for (char symbol : symbols) {
            System.out.printf("%3c", symbol);
        }
    }

    public static void main(String[] args) {
        char[] arrayUniqSymbols = new char[65535];
        int[] arrayUniqCounters = new int[65535];

        int index = 0;
        Scanner scan = new Scanner(System.in);
        char[] inputSymbols = scan.nextLine().toCharArray();
        scan.close();

        if (inputSymbols.length == 0) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        for (char c : inputSymbols) {
            if (isUniq(arrayUniqSymbols, c)) {
                arrayUniqSymbols[index] = c;
                arrayUniqCounters[index] = 1;
                countUniqSymbols += 1;
                index++;
            } else {
                arrayUniqCounters[pos] += 1;
            }
        }

        sort(arrayUniqSymbols, arrayUniqCounters);

        int len = (countUniqSymbols < 10) ? countUniqSymbols : 10;
        char[] topUniqSymbols = new char[len];
        int[] topCountSymbols = new int[len];

        for (int i = 0; i < len; i++) {
            topUniqSymbols[i] = arrayUniqSymbols[i];
            topCountSymbols[i] = arrayUniqCounters[i];
        }

        printHistogram(topUniqSymbols, topCountSymbols);
    }
}
