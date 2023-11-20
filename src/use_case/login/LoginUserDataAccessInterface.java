package use_case.login;

import entity.CommonUser;

public interface LoginUserDataAccessInterface {
    boolean userExists(String key);
    void save(CommonUser user);
    CommonUser get(String username);
}
