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


public class SpotifyApiCallAccessTokenDataAccessObject implements SpotifyApiCallInterface{

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
