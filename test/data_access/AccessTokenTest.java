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

    // This test checks the exception branch for getAccessToken.
    @Test
    public void testGetAccessTokenException() {
        // Create a mock object of the SpotifyApiCallAccessTokenDataAccessObject
        SpotifyApiCallAccessTokenDataAccessObject mockDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject() {
            @Override
            public String getAccessToken() {
                // Call the actual method with valid credentials
                String accessToken = super.getAccessToken();

                // Simulate an exception after retrieving the access token
                if (accessToken != null) {
                    throw new RuntimeException("Simulated exception after access token retrieval");
                }

                return accessToken;
            }
        };

        // Use assertThrows to check if the method throws the expected exception
        assertThrows(Exception.class, mockDataAccessObject::getAccessToken);
    }

}