package module02.ex01;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Program {
    public static ArrayList<String> inputA = new ArrayList<>();
    public static ArrayList<String> inputB = new ArrayList<>();
    public static TreeSet<String> dictionary = new TreeSet<>();
    private static final String  DICTIONARY_FILE = "dictionary.txt";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect number of arguments");
            System.exit(1);
        }

        inputA = readInputFile(args[0]);
        inputB = readInputFile(args[1]);
        dictionary.addAll(inputA);
        dictionary.addAll(inputB);

        writeDictionaryInFile();

        long[] frequencyInputA = calcFrequency(inputA);
        long[] frequencyInputB = calcFrequency(inputB);

        double calc = calcSimilarity(frequencyInputA, frequencyInputB);
        BigDecimal result = new BigDecimal(calc);
        result = result.setScale(2, RoundingMode.DOWN);
        System.out.printf("Similarity = %.2f\n", result);
    }

    public static ArrayList<String> readInputFile(String path) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.addAll(Arrays.asList(line.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeDictionaryInFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DICTIONARY_FILE)) ) {
            for (String word : dictionary) {
                bw.write(word + ' ');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long[] calcFrequency(ArrayList<String> input) {
        long[] result = new long[dictionary.size()];
        int pos = 0;
        for (String word : dictionary) {
            for (String wordInput : input) {
                if (word.equals(wordInput)) {
                    result[pos] += 1;
                }
            }
            pos++;
        }
        return result;
    }

    public static double calcSimilarity(long[] inputA, long[] inputB) {
        long numerator = 0;
        for (int i = 0; i < inputA.length; i++) {
            numerator += (inputA[i] * inputB[i]);
        }

        if (numerator == 0) {
            return 0;
        }

        long resA = 0;
        long resB = 0;
        for (int i = 0; i < inputA.length; i++) {
            resA += inputA[i] * inputA[i];
            resB += inputB[i] * inputB[i];
        }

        double denominator = Math.sqrt(resA) * Math.sqrt(resB);
        return numerator / denominator;
    }
}
