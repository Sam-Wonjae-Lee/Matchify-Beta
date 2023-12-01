package data_access;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccessTokenTest{

    // This test checks if the retrieved temporary access token is empty
    @Test
    public void testAccessTokenNotEmptyString() {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
//        System.out.println(accessToken);
        assertFalse("The access token should not be empty", accessToken.isEmpty());
    }

}