package data_access;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.json.JSONObject;

/*
 * SpotifyApiCallPlaylistItemsDataAccessObject contains the function to retrieve the "get playlist items" response.
 * */

public class SpotifyApiCallPlaylistItemsDataAccessObject implements SpotifyApiCallInterface {

    private final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;


    /**
     * Get playlist items (tracks) for a given playlist ID.
     * More info is located here: https://developer.spotify.com/documentation/web-api/reference/playlists/get-playlists-tracks/
     *
     * @param accessToken A string containing the temporary access token.
     * @param playlistId A string containing the Spotify user ID.
     * @return A JSONObject containing the response data for the items of the playlist.
     */
    public JSONObject getPlaylistItems(String accessToken, String playlistId)
            throws IOException, SpotifyWebApiException, InterruptedException, ExecutionException {

        // Initialize the Spotify API object
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(URI.create(REDIRECT_URI))
                .setAccessToken(accessToken)
                .build();

        // Create a request to get playlist items
        GetPlaylistsItemsRequest request = spotifyApi.getPlaylistsItems(playlistId)
                .limit(50) // You can adjust the limit as needed (50 is the max limit)
                .offset(0)  // You can adjust the offset as needed (offset should be 0)
                .build();

        // Execute the request asynchronously
        Future<Paging<PlaylistTrack>> pagingFuture = request.executeAsync();

        // Wait for the request to complete
        Paging<PlaylistTrack> playlistItems = pagingFuture.get();

        // Convert the playlist items to a JSONObject
        JSONObject playlistItemsJson = new JSONObject(playlistItems);

        return playlistItemsJson;
    }


}
