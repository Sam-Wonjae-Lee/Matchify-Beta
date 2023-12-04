package data_access;

import use_case.login.LoginSpotifyAPIDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemorySpotifyDataAccessObject implements LoginSpotifyAPIDataAccessInterface {

    private final Map<String, String> userNames = new HashMap<>();
    private final Map<String, String> profilePictures = new HashMap<>();
    private final Map<String, Boolean> userExists = new HashMap<>();

    @Override
    public String getName(String userID) {
        return userNames.get(userID);
    }

    @Override
    public String getProfilePicture(String userID) {
        return profilePictures.get(userID);
    }

    @Override
    public boolean userExists(String user_id) {
        return userExists.getOrDefault(user_id, false);
    }
}
