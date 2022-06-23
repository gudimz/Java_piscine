package module01.ex04;

public class Program {
    public static void main(String[] args)
            throws IllegalTransactionException, UserNotFoundException, TransactionNotFoundException {
        User user1 = new User("Max", 1500);
        System.out.println(user1);
        User user2 = new User("Anna", 1000);
        System.out.println(user2);
        User user3 = new User("Mary", 2000);
        System.out.println(user3);

        TransactionsService service = new TransactionsService();
        service.addUser(user1);
        service.addUser(user2);
        service.addUser(user3);

        System.out.println("===== call service.transferTransaction =====");
        service.transferTransaction(user1.getId(), user2.getId(), 1000);
        service.transferTransaction(user3.getId(), user2.getId(), -500);
        service.transferTransaction(user3.getId(), user1.getId(), 500);

//        System.out.println("===== throws IllegalTransactionException =====");
//        service.transferTransaction(user1.getId(), user2.getId(), 1000);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println("===== end =====\n");

        System.out.println("===== call service.getUserTransactions =====");
        Transaction[] transactionsUser1 = service.getUserTransactions(user1.getId());
        Transaction[] transactionsUser2 = service.getUserTransactions(user2.getId());
        Transaction[] transactionsUser3 = service.getUserTransactions(user3.getId());

        for (Transaction transaction : transactionsUser1) {
            System.out.println(transaction);
        }
        for (Transaction transaction : transactionsUser2) {
            System.out.println(transaction);
        }
        for (Transaction transaction : transactionsUser3) {
            System.out.println(transaction);
        }
        System.out.println("===== end =====\n");

        System.out.println("===== call service.removeUserTransaction =====");
        service.removeUserTransaction(transactionsUser1[0].getId(), user1.getId());
//        System.out.println("===== throws TransactionNotFoundException =====");
//        service.removeUserTransaction(transactionsUser1[0].getId(), user1.getId());
        System.out.println("===== end =====\n");

        System.out.println("===== check unpair =====");
        Transaction[] unPair = service.checkUnpairedTransactions();
        for (Transaction transaction : unPair) {
            System.out.println("unPair: " + transaction);
        }
        System.out.println("===== end =====\n");
    }
}
