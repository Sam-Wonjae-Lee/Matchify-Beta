package data_access;

import org.junit.Test;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ArtistIdsTest {

    // This test checks if the retrieved list of playlist Ids is the correct type (List<String>).
    @Test
    public void testArtistIdsType() throws IOException, ExecutionException, InterruptedException {
        SpotifyApiCallGetInfoDataAccessObject dataAccessObject = new SpotifyApiCallGetInfoDataAccessObject();

        // Playlist Id from https://open.spotify.com/playlist/16triPxo855oYUXNyNQHxR?si=f0785503f8a54e75
        String playlistId = "16triPxo855oYUXNyNQHxR";
        List<String> artistIds = dataAccessObject.getArtistsIds(playlistId);

        for (String artistId : artistIds) {
            System.out.println("Artist Id: " + artistId);
            assertTrue("Elements of artist Ids should be type String", artistId instanceof String);

        }

    }
}
