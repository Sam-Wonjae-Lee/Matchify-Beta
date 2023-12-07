package entity;

public class CommonUser implements User{
    private final String userID;
    private final FriendsList friendlist;
    private final Inbox inbox;
    private final Genre genres;

    private final String username;

    // Two constructors needed for creating the User and for user id.
    public CommonUser(String userID, FriendsList friendlist, Inbox inbox, Genre genres, String username) {
        this.userID = userID;
        this.friendlist = friendlist;
        this.inbox = inbox;
        this.genres = genres;
        this.username = username;
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

    public String getUsername(){
        return username;
    }
}
