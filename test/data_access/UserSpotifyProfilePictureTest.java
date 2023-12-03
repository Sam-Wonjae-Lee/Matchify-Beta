package data_access;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserSpotifyProfilePictureTest {

    @Test
    public void testUserProfilePictureNotEmptyString() throws IOException {
        SpotifyApiCallGetInfoDataAccessObject dataAccessObject = new SpotifyApiCallGetInfoDataAccessObject();

        // Spotify User ID for team member Wonjae Lee
        String userId = "ayimkorean";

        String profilePictureUrl = dataAccessObject.getUserProfilePicture(userId);

        System.out.println("Profile Picture: " + profilePictureUrl);

        assertFalse("The profile picture URL should not be empty", profilePictureUrl.isEmpty());
    }

}
