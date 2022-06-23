package module01.ex02;

public class UsersArrayList implements UsersList {
    private User[] users;
    private int capacity;
    private int size;

    UsersArrayList() {
        this.capacity = 10;
        this.size = 0;
        this.users = new User[capacity];
    }

    @Override
    public void addUser(User user) {
        if (size == capacity - 1) {
            capacity += capacity / 2;
            User[] tmp = new User[capacity];
            for (int i = 0; i < size; i++) {
                tmp[i] = users[i];
            }
            users = tmp;
        }
        users[size] = user;
        size++;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID: " + id + " not found!");
    }

    @Override
    public User getUserByIndex(int index) throws UserNotFoundException, ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= capacity) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (users[index] == null) {
            throw new UserNotFoundException("User with index: " + index + " in array was not found!");
        }
        return users[index];
    }

    @Override
    public int getNumberOfUsers() {
        return size;
    }
}
