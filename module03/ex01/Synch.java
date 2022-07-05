package module03.ex01;

public class Synch {
    private static boolean flag = false;
    public synchronized void sayEgg() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        System.out.println("Egg");
        notify();
    }

    public synchronized void sayHen() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        System.out.println("Hen");
        notify();
    }
}
