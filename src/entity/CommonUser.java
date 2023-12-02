package entity;

public class CommonUser implements User{
    private final String userID;
    private final FriendsList friends;
    private final Inbox inbox;

    // Two constructors needed for creating the User and for user id.
    public CommonUser(String userID, FriendsList friends, Inbox inbox) {
        this.userID = userID;
        this.friends = friends;
        this.inbox = inbox;
    }

    public String getUserID(){
        return userID;
    }

    public FriendsList getFriends(){
        return friends;
    }

    public Inbox getInbox(){
        return inbox;
    }
}
