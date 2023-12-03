package data_access;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileUserTest {
    // This test checks if the retrieved temporary access token is empty
    @Test
    public void testAccessCreateNewUsers() throws IOException {
        FileUserDataAccessObject obj = new FileUserDataAccessObject();
        obj.add_to_friendList("Frank", "David");
        obj.add_to_friendList("Frank", "Johnny");
        System.out.println(obj.userExists("frank"));
        assertTrue(obj.userExists("Frank"));
    }

    public void testAccessCreateTwoUsers() throws IOException {
        FileUserDataAccessObject obj = new FileUserDataAccessObject();
        obj.add_to_friendList("Frank", "David");
        obj.add_to_friendList("Frank", "Johnny");
        System.out.println(obj.userExists("frank"));
        assertTrue(obj.userExists("Frank"));
    }

    public void testAccess
}
