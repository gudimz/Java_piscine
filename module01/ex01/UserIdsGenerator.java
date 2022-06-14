package module01.ex01;

public class UserIdsGenerator {
    private static Integer id;

    UserIdsGenerator() {
        id = 0;
    }

    private static class SingletonHolder {
        private static final UserIdsGenerator INSTANCE = new UserIdsGenerator();
    }

    public static UserIdsGenerator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Integer generateId() {
        return id += 1;
    }
}
