package module02.ex02;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
            System.err.println("Bad argument");
            System.exit(1);
        }
        String path = args[0].substring(args[0].indexOf('=') + 1);
        Commands commands = new Commands(Paths.get(path));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    continue;
                }
                String[] cmd = line.split(" ");
                switch (cmd[0]) {
                    case "mv":
                        if (cmd.length == 3) {
                            commands.mv(cmd[1], cmd[2]);
                        } else {
                            System.out.println("Incorrect arguments for \"mv\". Please, enter the correct arguments");
                        }
                        break;
                    case "ls":
                        commands.ls();
                        break;
                    case "cd":
                        if (cmd.length == 2) {
                            commands.cd(cmd[1]);
                        } else {
                            System.out.println("Incorrect path for \"cd\". Please, enter the correct path");
                        }
                        break;
                    case "exit":
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Unknown command. Please, try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
