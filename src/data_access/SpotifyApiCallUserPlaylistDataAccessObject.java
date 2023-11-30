package data_access;

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

public class SpotifyApiCallUserPlaylistDataAccessObject implements SpotifyApiCallInterface{

    private static final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private static final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private static final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter access token: ");
        String accessToken = scanner.nextLine();

        System.out.println("Enter user Id: ");
        String userId = scanner.nextLine();

        // Initialize the Spotify API object
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(URI.create(REDIRECT_URI))
                .setAccessToken(accessToken)
                .build();

        // Get the user's playlists
        try {
            Paging<PlaylistSimplified> playlists = getUserPlaylists(spotifyApi, userId);
            for (PlaylistSimplified playlist : playlists.getItems()) {
                System.out.println("Playlist Name: " + playlist.getName());
                System.out.println("Playlist ID: " + playlist.getId());
                // Add more details as needed
            }
        } catch (IOException | SpotifyWebApiException | InterruptedException | ExecutionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Paging<PlaylistSimplified> getUserPlaylists(SpotifyApi spotifyApi, String userId)
            throws IOException, SpotifyWebApiException, InterruptedException, ExecutionException {
        // Create a request to get a user's playlists
        GetListOfUsersPlaylistsRequest request = spotifyApi.getListOfUsersPlaylists(userId)
                .limit(50) // You can adjust the limit as needed (50 is the max limit)
                .offset(0)  // You can adjust the offset as needed (offset should be 0)
                .build();

        // Execute the request asynchronously
        Future<Paging<PlaylistSimplified>> pagingFuture = request.executeAsync();

        // Wait for the request to complete
        return pagingFuture.get();
    }
}
