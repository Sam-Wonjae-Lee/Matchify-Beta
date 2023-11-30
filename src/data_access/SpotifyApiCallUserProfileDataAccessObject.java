package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;

import org.json.JSONObject;


public class SpotifyApiCallUserProfileDataAccessObject implements SpotifyApiCallInterface{

    // ClientId, ClientSecret, RedirectURI - necessary info for using API.
    private static final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private static final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private static final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;


    /**
    * Get the user's profile. In other words, get detailed profile information about the user.
    * More info is located here: https://developer.spotify.com/documentation/web-api/reference/get-current-users-profile
    *
    * @param accessToken A string containing the temporary access token.
    * @param userId A string containing the Spotify user ID.
    * @return A JSONObject containing the response data for the Spotify user.
    *
    * Example Response:
    * {"images":[{"width":64,"url":"https://i.scdn.co/image/ab67757000003b821e8b5038f93e5ec850728b8d","height":64},
    * {"width":300,"url":"https://i.scdn.co/image/ab6775700000ee851e8b5038f93e5ec850728b8d","height":300}],"followers":{"total":1,"href":null},
    * "href":"https://api.spotify.com/v1/users/o3bv345iz36uo33gj1ncpa8yo","id":"o3bv345iz36uo33gj1ncpa8yo","display_name":"David","type":"user",
    * "external_urls":{"spotify":"https://open.spotify.com/user/o3bv345iz36uo33gj1ncpa8yo"},"uri":"spotify:user:o3bv345iz36uo33gj1ncpa8yo"}
    */
    private static JSONObject getUserProfile(String accessToken, String userId) throws IOException {
        // Spotify API endpoint URL for user profile information
        String apiUrl = "https://api.spotify.com/v1/users/" + userId;

        // Create a URL object with the Spotify API endpoint
        URL url = new URL(apiUrl);

        // Open a connection to the Spotify API
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the user's access token
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        // Get the HTTP response code
        int responseCode = connection.getResponseCode();

        // Initialize a JSONObject to store response
        JSONObject responseData = null;

        // Check if the request was successful (HTTP status code 200)
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read and print the response from the Spotify API
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }

            // Close the reader
            bufferedReader.close();

            connection.disconnect();

            // Define response
            responseData = new JSONObject(response.toString());

        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            connection.disconnect();

            // Print error if response code not found
            System.out.println("Error 404");
        } else {
            connection.disconnect();

            // Throws exception
            throw new IOException("Response Code: " + responseCode);
        }

        // Prints response
        System.out.println(responseData);
        // Returns response
        return responseData;
    }


    // Used for printing the user profile response
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();

        System.out.println("Enter user Id: ");
        String userId = scanner.nextLine();

        try {
            getUserProfile(accessToken, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
