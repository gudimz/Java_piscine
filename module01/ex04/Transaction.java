
import java.util.UUID;

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    public enum TransferCategory {
        DEBIT,
        CREDIT
    }
    private TransferCategory category;
    private int amount;

    public Transaction(User recipient, User sender, int amount) throws IllegalTransactionException {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        setAmount(amount);

        if (this.category.equals(TransferCategory.CREDIT)) {
            if (sender.getBalance() >= this.amount) {
                this.sender.setBalance(amount * -1);
                this.recipient.setBalance(amount);
            } else {
                throw new IllegalTransactionException("The sender doesn't have enough money");
            }
        } else {
            if (recipient.getBalance() >= this.amount) {
                this.sender.setBalance(amount * -1);
                this.recipient.setBalance(amount);
            } else {
                throw new IllegalTransactionException("The recipient doesn't have enough money");
            }
        }
    }

    public Transaction(Transaction transaction) {
        this.id = transaction.getId();
        this.recipient = transaction.getSender();
        this.sender = transaction.getRecipient();
        this.amount = transaction.getAmount();
        if (transaction.getCategory().equals(TransferCategory.CREDIT)) {
            this.category = TransferCategory.DEBIT;
        } else {
            this.category = TransferCategory.CREDIT;
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

    public void setCategory(int amount) {
        this.category = (amount > 0) ? TransferCategory.CREDIT : TransferCategory.DEBIT;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = (amount > 0) ? amount : amount * -1;
        setCategory(amount);
    }

    @Override
    public String toString() {
        return "UUID: " + getId() + ", Recipient: " + getRecipient().getName() + ", Category: " + getCategory()
                + ", Sender: " + getSender().getName() + ", Amount " + getAmount();
    }

//    Transaction makeTransactionPair(User recipient, User sender, int amount, UUID id) {
//        return new Transaction(sender, recipient, amount, id);
//    }
}
