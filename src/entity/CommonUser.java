package entity;

import java.util.HashMap;
import java.util.Map;

public class CommonUser implements User{
    private final String userID;
    private final FriendsList friendlist;
    private final Inbox inbox;
    private final Genre genres;

    // Two constructors needed for creating the User and for user id.
    public CommonUser(String userID, FriendsList friendlist, Inbox inbox, Genre genres) {
        this.userID = userID;
        this.friendlist = friendlist;
        this.inbox = inbox;
        this.genres = genres;
    }

    public String getUserID(){
        return userID;
    }

    public FriendsList getFriendList(){
        return friendlist;
    }

    public Inbox getInbox(){
        return inbox;
    }

    public Genre getGenres() { return genres; }
}
