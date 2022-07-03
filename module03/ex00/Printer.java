package module03.ex00;

public class Printer implements Runnable {
    private final String name;
    private final int count;

    Printer(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
    }
}
