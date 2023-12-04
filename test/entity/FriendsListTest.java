package entity;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.*;

public class FriendsListTest {

    private FriendsList friendsList;

    @Before
    public void init() {
        // Initialize FriendsList
        friendsList = new FriendsList();
    }

    @Test
    public void testAddFriend() {
        // Add a friend to the list
        friendsList.add_friend("friend1");

        // Check if friend was added
        List<String> friendList = friendsList.get_friends();
        assertEquals(1, friendList.size());
        assertTrue(friendList.contains("friend1"));
    }

    @Test
    public void testRemoveFriend() {
        // Add friends to the list
        friendsList.add_friend("friend1");
        friendsList.add_friend("friend2");
        // Remove friend
        friendsList.remove_friend("friend1");
        // Check if friend was removed
        List<String> friendList = friendsList.get_friends();
        assertEquals(1, friendList.size());
        assertFalse(friendList.contains("friend1"));
        assertTrue(friendList.contains("friend2"));
    }

    @Test
    public void testGetFriends() {
        // Add friends to the list
        friendsList.add_friend("friend1");
        friendsList.add_friend("friend2");

        // Get the list of friends
        List<String> friendList = friendsList.get_friends();

        // Check if the list is not null and contains the added friends
        assertNotNull(friendList);
        assertEquals(2, friendList.size());
        assertTrue(friendList.contains("friend1"));
        assertTrue(friendList.contains("friend2"));
    }


}
