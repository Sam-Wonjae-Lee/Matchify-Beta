package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean userExists(String userId);

    // If user doesn't exist in csv file, we save it.
    // Otherwise, we don't
    void save(User user);

    User getUser(String userID);
}
