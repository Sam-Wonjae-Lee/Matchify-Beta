package entity;

import java.util.ArrayList;
import java.util.List;

public interface User {

    String getUserID();

    FriendsList getFriends();

    Inbox getInbox();
}
