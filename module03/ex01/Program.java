
public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Bad argument");
            System.exit(1);
        }

        int count = 0;
        try {
            count = Integer.parseInt(args[0].substring(8));
            if (count < 0) {
                System.err.println("Error: counter < 0");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: incorrect counter");
        }

        try {
            runPrinter(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runPrinter(int count) throws InterruptedException {
        Synch synch = new Synch();
        Thread egg = new Thread(new Printer("Egg", count, synch));
        Thread hen = new Thread(new Printer("Hen", count, synch));
        egg.start();
        hen.start();
    }
}