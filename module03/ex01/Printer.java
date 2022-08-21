
public class Printer implements Runnable {
    private final String name;
    private final int count;
    private final Synch SYNCH;

    Printer(String name, int count, Synch synch) {
        this.name = name;
        this.count = count;
        this.SYNCH = synch;
    }

    @Override
    public void run() {
            for (int i = 0; i < count; i++) {
                if (name.equals("Egg")) {
                    SYNCH.sayEgg();
                } else if (name.equals("Hen")) {
                    SYNCH.sayHen();
                }
            }
    }
}
