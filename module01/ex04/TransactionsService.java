
import java.util.UUID;

public class TransactionsService {
    private UsersList userList;

    public TransactionsService() {
        this.userList = new UsersArrayList();
    }

    public void addUser(User user) {
        userList.addUser(user);
    }

    public int getUserBalance(int id) throws UserNotFoundException {
        return userList.getUserById(id).getBalance();
    }

    public void transferTransaction(int idRecipient, int idSender, int amount)
            throws UserNotFoundException, IllegalTransactionException {
        User recipient = userList.getUserById(idRecipient);
        User sender = userList.getUserById(idSender);

        Transaction transaction = new Transaction(recipient, sender, amount);
        recipient.getTransactions().addTransaction(transaction);
        sender.getTransactions().addTransaction(new Transaction(transaction));
    }

    public Transaction[] getUserTransactions(int id) throws UserNotFoundException {
        return userList.getUserById(id).getTransactions().toArray();
    }

    public void removeUserTransaction(UUID id, int userId)
            throws UserNotFoundException, TransactionNotFoundException {
        userList.getUserById(userId).getTransactions().removeById(id);
    }

    public Transaction[] checkUnpairedTransactions()
            throws UserNotFoundException, TransactionNotFoundException {
        TransactionsList debitList = new TransactionsLinkedList();
        TransactionsList creditList = new TransactionsLinkedList();
        TransactionsList unpairedList = new TransactionsLinkedList();

        for (int i = 0; i < userList.getNumberOfUsers(); i++) {
            Transaction[] transactions = userList.getUserByIndex(i).getTransactions().toArray();
            for (Transaction transaction : transactions) {
                if (transaction.getCategory().equals(Transaction.TransferCategory.CREDIT)) {
                    creditList.addTransaction(transaction);
                } else {
                    debitList.addTransaction(transaction);
                }
            }
        }

        Transaction[] creditArray = creditList.toArray();
        Transaction[] debitArray = debitList.toArray();
        for (Transaction credit : creditArray) {
            boolean isPair = false;
            for (Transaction debit : debitArray) {
                if (credit.getId().equals(debit.getId())) {
                    isPair = true;
                    debitList.removeById(debit.getId());
                    break;
                }
            }
            if (!isPair) {
                unpairedList.addTransaction(credit);
            }
        }
        for (Transaction transaction : debitList.toArray()) {
            unpairedList.addTransaction(transaction);
        }
        return unpairedList.toArray();
    }
}