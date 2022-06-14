package module01.ex02;

public class Program {
    public static void main(String[] args) throws UserNotFoundException {
        UsersArrayList arrayUsers = new UsersArrayList();

        System.out.println("===== get user by index =====");
        for (int i = 0; i < 15; i++) {
            arrayUsers.addUser(new User("name #" + i, 21 * i));
            System.out.println(arrayUsers.getUserByIndex(i));
        }

        System.out.println("\n===== add user by ID =====");
        for (int i = 1; i <= 15; i++) {
            System.out.println(arrayUsers.getUserById(i));
        }

        System.out.println("\n===== get number of users =====");
        System.out.println(arrayUsers.getNumberOfUsers());

//        System.out.println("\n===== throw UserNotFoundException =====");
//        System.out.println("arrayUsers.getUserByIndex(16)");
//        System.out.println(arrayUsers.getUserByIndex(16));

//        System.out.println("arrayUsers.getUserById(16)");
//        System.out.println(arrayUsers.getUserById(16));
    }
}
