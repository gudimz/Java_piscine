
public class Calculator implements Runnable {
    private int num;
    private int sum;
    private final int start;
    private final int end;
    private final int[] numbers;

    public Calculator(int num, int[] arrayNumbers, int start, int end) {
        this.num = num;
        this.sum = 0;
        this.numbers = arrayNumbers;
        this.start = start;
        this.end = end;
    }

    public int getNum() {
        return num;
    }

    public int getSum() {
        return sum;
    }

    public int getStart() {
        return start;
    }


    public int getEnd() {
        return end;
    }

    public synchronized void addSumFromThreads() {
        Program.sumFromThreads += this.sum;
        num++;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            this.sum += number;
        }
        System.out.println("Thread " + getNum() + ": from " + getStart() + " to " + getEnd()
                + " sum is " + getSum());
        addSumFromThreads();
    }
}
