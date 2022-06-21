package module01.ex03;

public class User {
    private final Integer id;
    private String name;
    private Integer balance;
    private TransactionsLinkedList transactions;

    public User(String name, Integer balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = (balance > 0) ? balance : 0;
        this.transactions = new TransactionsLinkedList();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public TransactionsLinkedList getTransactions() {
        return this.transactions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
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
