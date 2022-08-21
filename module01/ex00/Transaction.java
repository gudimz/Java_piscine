
import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private enum TransferCategory {
        DEBIT,
        CREDIT
    }
    private TransferCategory category;
    private Integer amount;

    public Transaction(User recipient, User sender, Integer amount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        setAmount(amount);

        if (this.category.equals(TransferCategory.CREDIT)) {
            if (sender.getBalance() >= this.amount) {
                this.sender.setBalance(amount * -1);
                this.recipient.setBalance(amount);
            } else {
                System.err.println("Error! The sender doesn't have enough money");
                System.exit(-1);
            }
        } else {
            if (recipient.getBalance() >= this.amount) {
                this.sender.setBalance(amount * -1);
                this.recipient.setBalance(amount);
            } else {
                System.err.println("Error! The recipient doesn't have enough money");
                System.exit(-1);
            }
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public TransferCategory getCategory() {
        return category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = (amount > 0) ? amount : amount * -1;
        this.category = (amount > 0) ? TransferCategory.CREDIT : TransferCategory.DEBIT;
    }

    @Override
    public String toString() {
        return "UUID: " + getId() + ", Recipient: " + getRecipient().getName() + ", Category: " + getCategory()
                + ", Sender: " + getSender().getName() + ", Amount " + getAmount();
    }
}
