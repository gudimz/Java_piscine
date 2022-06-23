package module01.ex04;

public class User {
    private final int id;
    private String name;
    private int balance;
    private TransactionsLinkedList transactions;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = (balance > 0) ? balance : 0;
        this.transactions = new TransactionsLinkedList();
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

    public TransactionsLinkedList getTransactions() {
        return this.transactions;
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
