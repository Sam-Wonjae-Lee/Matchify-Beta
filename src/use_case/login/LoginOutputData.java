package use_case.login;

import entity.FriendsList;
import entity.Inbox;

public class LoginOutputData {

    private final String userID;
    private final String username;

    private final String pfp;

    private final FriendsList friendsList;

    private boolean useCaseFailed;

    public LoginOutputData(String userId, String username, String pfp, FriendsList friendsList, boolean useCaseFailed) {
        this.userID = userId;
        this.username = username;
        this.pfp = pfp;
        this.friendsList = friendsList;
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

    public FriendsList getFriendsList(){
        return friendsList;
    }
}
