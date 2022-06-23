package module01.ex04;

public interface UsersList {
    void addUser(User user);
    User getUserById(int id) throws UserNotFoundException;
    User getUserByIndex(int index) throws UserNotFoundException;
    int getNumberOfUsers();
}
