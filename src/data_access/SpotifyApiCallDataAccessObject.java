package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Scanner;

// JSON Array
import org.json.JSONArray;
import org.json.JSONObject;


public class SpotifyApiCallDataAccessObject {

    /**
    * Get the current user's profile. In other words, get detailed profile information about the current user.
    * More info is located here: https://developer.spotify.com/documentation/web-api/reference/get-current-users-profile
    * @return A JSONArray containing the response data for the current Spotify user.
    * */
    private static JSONObject getUserProfile(String accessToken) throws IOException {
        // Spotify API endpoint for user profile information
        String apiUrl = "https://api.spotify.com/v1/me";

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

        // Initialize a JSONArray to store response
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

            responseData = new JSONObject(response.toString());

        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            connection.disconnect();

            System.out.println("Error 404");
        } else {
            connection.disconnect();

            throw new IOException("Response Code: " + responseCode);
        }

        System.out.println(responseData);
        return responseData;
    }


    /**
     * Retrieves the access token. The access token is a string which contains the credentials and permissions that can be used to access resources.
     * The access token is valid for 1 hour. After that time, the token expires and you need to request a new one.
     * More info is located here: https://developer.spotify.com/documentation/web-api/concepts/access-token
     *
     * @return A string containing the temporary access token.
     * @throws Exception if access token cannot be retrieved.
     */

//    public static String getAccessToken() {
//        // Client ID and Client Secret from Spotify Dashboard
//        String clientId = "9ed5f6af048844e4851425fbc416ae10";
//        String clientSecret = "df75314d40634c9db0d1da481a2302e8";
//
//        // Spotify API endpoints
//        String tokenUrl = "https://accounts.spotify.com/api/token";
//
//        // Base64 encode the client ID and client secret
//        String credentials = clientId + ":" + clientSecret;
//        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
//
//        // Build the authorization header
//        String authorizationHeader = "Basic " + base64Credentials;
//
//        // Build the request body for token retrieval
//        String requestBody = "grant_type=client_credentials";
//
//        // Create an HTTP client
//        HttpClient client = HttpClient.newHttpClient();
//
//        // Build the request
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(tokenUrl))
//                .header("Authorization", authorizationHeader)
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        try {
//            // Send the request and get the response
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Parse the JSON response to extract the access token
//            String accessToken = response.body().split("\"access_token\":\"")[1].split("\"")[0];
//
//            return accessToken;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    // Used for printing the access token
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter: ");

        String accessToken = scanner.nextLine();

        try {
            getUserProfile(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
