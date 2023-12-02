package entity;

public class CommonUser implements User{
    private final String userID;
    private final FriendsList friendlist;
    private final Inbox inbox;

    // Two constructors needed for creating the User and for user id.
    public CommonUser(String userID, FriendsList friendlist, Inbox inbox) {
        this.userID = userID;
        this.friendlist = friendlist;
        this.inbox = inbox;
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
}
