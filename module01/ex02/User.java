package module01.ex02;

public class User {
    private final Integer id;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = (balance > 0) ? balance : 0;
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
