package data_access;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccessTokenTest{

    // This test checks if the retrieved temporary access token is empty
    @Test
    public void testAccessTokenNotEmptyString() {
        SpotifyApiCallAccessTokenDataAccessObject dataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        String accessToken = dataAccessObject.getAccessToken();
        System.out.println("Access Token: " + accessToken);
        assertFalse("The access token should not be empty", accessToken.isEmpty());
    }

}