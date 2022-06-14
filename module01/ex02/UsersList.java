package module01.ex02;

public interface UsersList {
    void addUser(User user);
    User getUserById(Integer id) throws UserNotFoundException;
    User getUserByIndex(Integer index) throws UserNotFoundException;
    Integer getNumberOfUsers();
}
