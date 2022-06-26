package module02.ex00;

import java.io.*;
import java.util.*;

public class Program {
    private static final String  SIGNATURES_FILE = "signatures.txt";
    private static final String  RESULT_FILE = "result.txt";

    public static void main(String[] args) throws IOException {
        HashMap<String, String> signatures = parseSignatures();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String path = scanner.nextLine();
            if (path.equals("42")) {
                scanner.close();
                break;
            }
            String fileSignature = findFileSignature(path);
            boolean flag = false;
            for (String key : signatures.keySet()) {
                if (fileSignature.contains(signatures.get(key))) {
                    writeResultFile(key);
                    System.out.println("PROCESSED");
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("UNDEFINED");
            }
        }
        scanner.close();
    }

    public static HashMap<String, String> parseSignatures() {
        HashMap<String, String> result = new HashMap<>();
        try (FileInputStream input = new FileInputStream(SIGNATURES_FILE)) {
            StringBuilder builder = new StringBuilder();
            int i;
            while ((i = input.read()) != -1) {
                if ((char) i == '\n' && builder.length() != 0) {
                    String[] line = builder.toString().split(",");
                    result.put(line[0].trim(), line[1].trim());
                    builder.setLength(0);
                } else {
                    if ((char) i != '\n' && (char) i != ' ') {
                        builder.append((char) i);
                    }
                }
            }
            if (builder.length() != 0) {
                String[] line = builder.toString().split(",");
                result.put(line[0].trim(), line[1].trim());
                builder.setLength(0);
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String findFileSignature(String path) {
        StringBuilder builder = new StringBuilder();
        try (FileInputStream input = new FileInputStream(path)) {
            int byteCount = 20;
            int i;
            while ((i = input.read()) != -1 && byteCount > 0) {
                builder.append(Integer.toHexString(i).toUpperCase());
                byteCount--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void writeResultFile(String key) {
        try (FileOutputStream output = new FileOutputStream(RESULT_FILE, true)) {
            output.write(key.getBytes());
            output.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
