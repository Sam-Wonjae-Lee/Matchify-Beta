package entity;


public interface User {

    String getUserID();

    FriendsList getFriendList();

    Inbox getInbox();

    Genre getGenres();

    String getUsername();
}
