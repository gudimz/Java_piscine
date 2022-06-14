package module01.ex02;

public class UsersArrayList implements UsersList {
    private User[] users;
    private Integer capacity;
    private Integer size;

    UsersArrayList() {
        this.capacity = 10;
        this.size = 0;
        this.users = new User[capacity];
    }

    @Override
    public void addUser(User user) {
        if (size.equals(capacity - 1)) {
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
    public User getUserById(Integer id) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (id.equals(users[i].getId())) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID: " + id + " not found!");
    }

    @Override
    public User getUserByIndex(Integer index) throws UserNotFoundException, ArrayIndexOutOfBoundsException {
        if (index < 0 || index.compareTo(capacity) > 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (users[index] == null) {
            throw new UserNotFoundException("User with index: " + index + " in array was not found!");
        }
        return users[index];
    }

    @Override
    public Integer getNumberOfUsers() {
        return size;
    }
}
