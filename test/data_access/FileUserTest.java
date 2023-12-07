package data_access;

import entity.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FileUserTest {
    // This test checks if the retrieved temporary access token is empty
    @Test
    public void testAccessCreateNewUsers() throws IOException {

        CommonUserFactory fact = new CommonUserFactory();
        FileUserDataAccessObject obj1 = new FileUserDataAccessObject(fact);
        obj1.clear_all_files();
        FileUserDataAccessObject obj = new FileUserDataAccessObject(fact);
        Genre genre = new Genre();
        HashMap<String, Integer> userGenre = new HashMap<>();
        genre.setGenreMap(userGenre);
        obj.add_user_genre("frank",userGenre);

        FriendsList lst = new FriendsList();
        Inbox inbox = new Inbox();
        String username = "gwen";
        User user = new CommonUser("frank", lst, inbox, genre, username);
        obj.save(user);

        assertTrue(obj.userExists("frank"));
    }

    @Test
    public void testAccessCreateTwoUsers() throws IOException {
        CommonUserFactory fact = new CommonUserFactory();
        FileUserDataAccessObject obj1 = new FileUserDataAccessObject(fact);
        obj1.clear_all_files();
        FileUserDataAccessObject obj = new FileUserDataAccessObject(fact);
        Genre genre = new Genre();
        HashMap<String, Integer> userGenre = new HashMap<>();
        genre.setGenreMap(userGenre);
        obj.add_user_genre("frank",userGenre);

        FriendsList lst = new FriendsList();
        Inbox inbox = new Inbox();
        String username = "gwen";
        User user = new CommonUser("frank", lst, inbox, genre, username);
        User user2 = new CommonUser("david", lst, inbox, genre, username);
        obj.save(user);
        obj.save(user2);

        assertTrue(obj.userExists("frank"));
        assertTrue(obj.userExists("david"));

    }

}
