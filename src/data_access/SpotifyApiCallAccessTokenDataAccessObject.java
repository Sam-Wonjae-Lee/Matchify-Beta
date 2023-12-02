package data_access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

/*
* SpotifyApiCallAccessTokenDataAccessObject contains the function to retrieve the temporary access token.
* */

public class SpotifyApiCallAccessTokenDataAccessObject implements SpotifyApiCallInterface {

    // ClientId, ClientSecret, RedirectURI - necessary info for using API.
    public String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;

    /**
     * Retrieves the access token. The access token is a string which contains the credentials and permissions that can be used to access resources.
     * The access token is valid for 1 hour. After that time, the token expires and you need to request a new one.
     * More info is located here: https://developer.spotify.com/documentation/web-api/concepts/access-token
     *
     * @return A string containing the temporary access token.
     * @throws Exception if access token cannot be retrieved.
     */
    public static String getAccessToken() {
        // Client ID and Client Secret from Spotify Dashboard
        String clientId = SpotifyApiCallInterface.CLIENT_ID;
        String clientSecret = SpotifyApiCallInterface.CLIENT_SECRET;
        String redirectUri = SpotifyApiCallInterface.REDIRECT_URI;

        // Spotify API endpoints
        String tokenUrl = "https://accounts.spotify.com/api/token";

        // Base64 encode the client ID and client secret
        String credentials = clientId + ":" + clientSecret;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        // Build the authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        // Build the request body for token retrieval
        String requestBody = "grant_type=client_credentials";

        // Create HTTP client
        HttpClient httpClient = HttpClient.newHttpClient();

        // Build the request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response to extract the access token
            String accessToken = response.body().split("\"access_token\":\"")[1].split("\"")[0];

            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /* This is example code */
//    public static void main(String[] args) {
//        String accessToken = getAccessToken();
//
//        if (accessToken != null) {
//            System.out.println("Access Token: " + accessToken);
//        } else {
//            System.out.println("Failed to retrieve access token.");
//        }
//
//    }

}
