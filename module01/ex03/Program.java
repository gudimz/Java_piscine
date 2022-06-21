package module01.ex03;

public class Program {
    public static void main(String[] args) throws TransactionNotFoundException {
        User user1 = new User("Anna", 1000);
        User user2 = new User("Max", 1500);

        Transaction transaction1 = new Transaction(user1, user2, 1000);
        Transaction transaction2 = new Transaction(user1, user2, -2000);
        Transaction transaction3 = new Transaction(user1, user2, 500);

        System.out.println("===== add transactions to list =====");

        user1.getTransactions().addTransaction(transaction1);
        user1.getTransactions().addTransaction(transaction2);
        user1.getTransactions().addTransaction(transaction3);

        Transaction[] arrayTransactions = user1.getTransactions().toArray();
        for (Transaction trans : arrayTransactions) {
            System.out.println(trans);
        }

        System.out.println("===== remove transaction from list =====");
        user1.getTransactions().removeById(transaction1.getId());
        user1.getTransactions().removeById(transaction2.getId());
        user1.getTransactions().removeById(transaction3.getId());

        arrayTransactions = user1.getTransactions().toArray();
        for (Transaction trans : arrayTransactions) {
            System.out.println(trans);
        }

//        System.out.println("\n===== throw TransactionNotFoundException =====");
//        user1.getTransactions().removeById(transaction3.getId());
    }
}
