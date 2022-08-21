
public class User {
    private final int id;
    private String name;
    private int balance;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = (balance > 0) ? balance : 0;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        if (balance >= 0) {
            this.balance += balance;
        } else {
            this.balance = (this.balance - balance > 0) ? this.balance + balance : 0;
        }
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", USERNAME: " + getName() + ", BALANCE: " + getBalance();
    }
}
