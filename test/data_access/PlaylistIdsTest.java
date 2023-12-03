package data_access;

import org.junit.Test;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import use_case.match.MatchSpotifyAccessInterface;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

public class PlaylistIdsTest {

    // This test checks if the retrieved list of playlist Ids is the correct type (List<String>).
    @Test
    public void testPlaylistIdsType() {

        // User ID that is used as an example in documentation: https://developer.spotify.com/documentation/web-api/reference/get-list-users-playlists
        String userId = "smedjan";
        MatchSpotifyAccessInterface spotifyDataAccessObject = new SpotifyApiCallGetInfoDataAccessObject();
        List<String> playlistIds = spotifyDataAccessObject.getPlaylistIds(userId);

        for (String playlistId : playlistIds) {
            System.out.println("Playlist ID: " + playlistId);
            assertTrue("Elements of playlist Ids should be type String", playlistId instanceof String);

        }

    }
}
