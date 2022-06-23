package module01.ex02;

public class UserIdsGenerator {
    private static int id;

    private UserIdsGenerator() {
        id = 0;
    }

    private static class SingletonHolder {
        private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();
    }

    public static UserIdsGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public int generateId() {
        return id += 1;
    }
}
