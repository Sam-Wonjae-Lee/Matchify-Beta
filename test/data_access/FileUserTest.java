package data_access;

import entity.CommonUserFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileUserTest {
    // This test checks if the retrieved temporary access token is empty
    @Test
    public void testAccessCreateNewUsers() throws IOException {
        CommonUserFactory fact = new CommonUserFactory();
        FileUserDataAccessObject obj = new FileUserDataAccessObject(fact);
        obj.add_to_friendList("Frank", "David");
        obj.add_to_friendList("Frank", "Johnny");
        System.out.println(obj.userExists("frank"));
        assertTrue(obj.userExists("Frank"));
    }

    @Test
    public void testAccessCreateTwoUsers() throws IOException {
        CommonUserFactory fact = new CommonUserFactory();
        FileUserDataAccessObject obj = new FileUserDataAccessObject(fact);
        obj.add_to_friendList("Frank", "David");
        obj.add_to_friendList("Frank", "Johnny");
        System.out.println(obj.userExists("frank"));
        assertTrue(obj.userExists("Frank"));
    }

}
