package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User{
    private final String userID;
    private final List<String> friends;
    private final List<String> inbox;

    // Two constructors needed for creating the User and for user id.
    public CommonUser(String userID, List<String> friends, List<String> inbox) {
        this.userID = userID;
        this.friends = friends;
        this.inbox = inbox;
    }

    public String getUserID(){
        return userID;
    }

    public List<String> getFriends(){
        return friends;
    }

    public List<String> getInbox(){
        return inbox;
    }
}
