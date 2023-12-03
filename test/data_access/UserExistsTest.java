package data_access;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserExistsTest {

    @Test
    public void testUserExistsTrue() {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallUserProfileDataAccessObject dataAccessObject = new SpotifyApiCallUserProfileDataAccessObject();

        // Wonjae's valid Spotify user Id (Valid user Id).
        String userId = "ayimkorean";

        boolean result = dataAccessObject.checkUserExists(accessTokenDataAccessObject.getAccessToken(), userId);

        assertTrue(result);
    }

    @Test
    public void testUserExistsFalse() {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallUserProfileDataAccessObject dataAccessObject = new SpotifyApiCallUserProfileDataAccessObject();

        // Not a valid user Id.
        String userId = "aadwadwadawsdadw";

        boolean result = dataAccessObject.checkUserExists(accessTokenDataAccessObject.getAccessToken(), userId);

        assertFalse(result);
    }

}
