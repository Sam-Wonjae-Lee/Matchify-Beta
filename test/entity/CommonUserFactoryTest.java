package entity;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CommonUserFactoryTest {

    @Test
    public void create() {
        // Create a CommonUserFactory
        CommonUserFactory userFactory = new CommonUserFactory();

        // Create a FriendList
        FriendsList friendsList = new FriendsList();
        friendsList.add_friend("friend1");

        // Creat an Inbox
        Inbox inbox = new Inbox();
        inbox.add_invite("invite1");

        // Create a CommonUser with friendsList and inbox
        CommonUser user = userFactory.create("123", friendsList, inbox);

        // Verify the user is created is the right ID
        assertEquals("123", user.getUserID());

        // Verify the user has the correct friendsList
        List<String> userFriends = user.getFriendList().get_friends();
        assertEquals(1, userFriends.size());
        assertTrue(userFriends.contains("friend1"));

        // Verify the user has the correct inbox
        List<String> userInvites = user.getInbox().get_invites();
        assertEquals(1, userInvites.size());
        assertTrue(userInvites.contains("invite1"));
    }
}