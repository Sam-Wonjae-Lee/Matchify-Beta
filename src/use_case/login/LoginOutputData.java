package use_case.login;

import java.util.HashMap;
import java.util.List;

public class LoginOutputData {

    private final String userID;
    private final String username;

    private final String pfp;

    private final List<String> friends;

    private final HashMap<String, String> idMap;

    public LoginOutputData(String userId, String username, String pfp, List<String> friendsList, HashMap<String, String> idMap) {
        this.userID = userId;
        this.username = username;
        this.pfp = pfp;
        this.friends = friendsList;
        this.idMap = idMap;
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

    public HashMap<String, String> getIdMap(){
        return idMap;
    }
}
