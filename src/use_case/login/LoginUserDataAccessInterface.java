package use_case.login;

import entity.CommonUser;

public interface LoginUserDataAccessInterface {
    boolean userExists(String userId);

    // If user doesn't exist in csv file, we save it.
    // Otherwise, we don't
    void save(CommonUser user);

    String getUsername(String userId);
    String getProfilePictureUrl(String userId);
}
