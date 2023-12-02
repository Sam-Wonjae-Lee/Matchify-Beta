package data_access;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UsernameSpotifyTest {

    // This test checks if the retrieved username is the correct type (String).
    @Test
    public void testUsernameNotEmptyString() throws IOException {
        // Spotify User ID for team member David Li
        String userId = "o3bv345iz36uo33gj1ncpa8yo";

        String username = SpotifyApiCallGetInfoDataAccessObject.getUsername(userId);

        System.out.println("Username: " + username);

        assertFalse("The username should not be empty", username.isEmpty());
    }

}
