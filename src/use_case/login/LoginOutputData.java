package use_case.login;

import entity.FriendsList;
import entity.Inbox;

import java.util.ArrayList;
import java.util.List;

public class LoginOutputData {

    private final String userID;
    private final String username;

    private final String pfp;

    private final List<String> friends;

    private boolean useCaseFailed;

    public LoginOutputData(String userId, String username, String pfp, List<String> friendsList, boolean useCaseFailed) {
        this.userID = userId;
        this.username = username;
        this.pfp = pfp;
        this.friends = friendsList;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID(){
        return userID;
    }

    public String getPfp(){
        return pfp;
    }

    public List<String> getFriendsList(){
        return friends;
    }
}
