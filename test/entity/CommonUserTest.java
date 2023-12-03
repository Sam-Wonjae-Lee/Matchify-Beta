package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Before.*;
import static org.junit.Test.*;
import static org.junit.Assert.*;

public class CommonUserTest {

    private CommonUser user;
    private FriendsList friendsList;
    private Inbox inbox;

    @Before
    public void init() {
        friendsList = new FriendsList() ;
        inbox = new Inbox() ;
        user = new CommonUser("123", friendsList, inbox);
    }

    @Test
    public void getUserID() {
        assertEquals("123", user.getUserID());
    }

    @Test
    public void getFriendList() {
        assertEquals(friendsList, user.getFriendList());
    }

    @Test
    public void getInbox() {
        assertEquals(inbox, user.getInbox());
    }
}