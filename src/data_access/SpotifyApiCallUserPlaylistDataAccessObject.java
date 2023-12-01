package data_access;

import org.json.JSONObject;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*
 * SpotifyApiCallArtistGenresDataAccessObject contains the function to retrieve the "get user's playlist" response.
 * */

public class SpotifyApiCallUserPlaylistDataAccessObject implements SpotifyApiCallInterface{

    private static final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private static final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private static final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();

        System.out.println("Enter user Id: ");
        String userId = scanner.nextLine();

        // Get the user's playlists
        try {
            JSONObject playlists = getUserPlaylists(accessToken, userId);
            System.out.println(playlists);
        } catch (IOException | SpotifyWebApiException | InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Get the user's playlist information. This consists information from the playlist like images, playlist id, etc.
     * More info is located here: https://developer.spotify.com/documentation/web-api/reference/get-list-users-playlists
     *
     * @param accessToken A string containing the temporary access token.
     * @param userId A string containing the Spotify user ID.
     * @return A JSONObject containing the response data for the user's playlist.
     * */
    public static JSONObject getUserPlaylists(String accessToken, String userId)
            throws IOException, SpotifyWebApiException, InterruptedException, ExecutionException {

        // Initialize the Spotify API object
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(URI.create(REDIRECT_URI))
                .setAccessToken(accessToken)
                .build();

        // Create a request to get a user's playlists
        GetListOfUsersPlaylistsRequest request = spotifyApi.getListOfUsersPlaylists(userId)
                .limit(50) // You can adjust the limit as needed (50 is the max limit)
                .offset(0)  // You can adjust the offset as needed (offset should be 0)
                .build();

        // Execute the request asynchronously
        Future<Paging<PlaylistSimplified>> pagingFuture = request.executeAsync();
        // Wait for the request to complete
        Paging<PlaylistSimplified> playlists = pagingFuture.get();

        // Convert the playlists to a JSONObject
        JSONObject responseData = new JSONObject(playlists);

        return responseData;
    }
}
