package entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUserTest {

    private CommonUser user;
    private FriendsList friendsList;
    private Inbox inbox;
    private Genre genre;

    @Before
    public void init() {
        friendsList = new FriendsList() ;
        inbox = new Inbox() ;
        genre = new Genre();
        user = new CommonUser("123", friendsList, inbox, genre, username);
    }
//    HashMap<String, Map<String, Integer>> genres
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

    @Test
    public void getGenre() { assertEquals(genre, user.getGenres()); }
}