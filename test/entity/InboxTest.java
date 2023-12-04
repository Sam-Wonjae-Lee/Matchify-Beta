package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InboxTest {

    private Inbox inbox;

    @Before
    public void setUp() {
        // Initialize an Inbox object before each test
        inbox = new Inbox();
    }

    @Test
    public void testAddInvite() {
        // Test adding an invite to the inbox
        inbox.add_invite("user1");
        assertTrue(inbox.get_invites().contains("user1"));
    }

    @Test
    public void testRemoveInvite() {
        // Test removing an invite from the inbox
        inbox.add_invite("user2");
        inbox.remove_invite("user2");
        assertFalse(inbox.get_invites().contains("user2"));
    }

    @Test
    public void testGetInvites() {
        // Test getting the list of invites from the inbox
        inbox.add_invite("user3");
        inbox.add_invite("user4");
        assertEquals(2, inbox.get_invites().size());
        assertTrue(inbox.get_invites().contains("user3"));
        assertTrue(inbox.get_invites().contains("user4"));
    }
}
