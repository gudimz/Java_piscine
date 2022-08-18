package module03.ex03;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class Program {
    public static HashMap<Integer, URL> urls = new HashMap<>();
    public static int threadsCount = 0;
    public static final String DICTIONARY_TO_UPLOAD = "upload/";
    public static final String FILE_WITH_URLS = "files_urls.txt";

    public static void parseUrls() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_WITH_URLS))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.substring(0, line.indexOf("http")).trim());
                URL url = new URL(line.substring(line.indexOf("http")).trim());
                urls.put(number, url);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.err.println("Bad argument");
            System.exit(1);
        }

        threadsCount = Integer.parseInt(args[0].substring(15).trim());
        parseUrls();

        if (!Files.exists(Paths.get(DICTIONARY_TO_UPLOAD))) {
            Files.createDirectory(Paths.get(DICTIONARY_TO_UPLOAD));
        }

        ArrayList<Thread> threads = new ArrayList<>(threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            threads.add(new Thread(new Downloader(urls)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
