package module01.ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Anna", 1000);
        System.out.println(user1);
        User user2 = new User("Max", 1500);
        System.out.println(user2);

        Transaction MaxToAnna = new Transaction(user1, user2, 1000);
        System.out.println(MaxToAnna);
        System.out.println(user1);
        System.out.println(user2);

        Transaction AnnaToMax = new Transaction(user1, user2, -2000);
        System.out.println(AnnaToMax);
        System.out.println(user1);
        System.out.println(user2);
    }
}
