package edu.school21.printer.app;
import edu.school21.printer.logic.Converter;

import java.io.*;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Error: Bad argument");
            System.exit(1);
        }
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        String path = args[2];

        try {
            Converter converter = new Converter(white, black, path);
            converter.print();

        } catch (IOException e) {
            System.err.println("Error: No such file");
        }
    }
}
