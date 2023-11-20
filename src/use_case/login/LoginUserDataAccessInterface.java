package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean userExists(String key);
    void save(User user);
    User get(String username);
}
