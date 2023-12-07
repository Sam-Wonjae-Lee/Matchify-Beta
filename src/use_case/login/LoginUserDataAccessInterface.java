package use_case.login;

import entity.User;

import java.util.HashMap;
import java.util.Map;

public interface LoginUserDataAccessInterface {
    boolean userExists(String userId);

    // If user doesn't exist in csv file, we save it.
    // Otherwise, we don't
    void save(User user);

    User getUser(String userID);

    void add_user_genre(String userID, HashMap<String, Integer> genre);

    HashMap<String, String> getUsernameMap();
}