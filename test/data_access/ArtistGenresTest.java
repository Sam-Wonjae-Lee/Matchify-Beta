package data_access;

import org.apache.hc.core5.http.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ArtistGenresTest {

    // This test checks if the retrieved list of genres is the correct type (List<String>).
    @Test
    public void testArtistGenresType() throws IOException, ParseException, SpotifyWebApiException {
        SpotifyApiCallGetInfoDataAccessObject dataAccessObject = new SpotifyApiCallGetInfoDataAccessObject();

        // Artist ID for J.Cole
        String artistId = "6l3HvQ5sa6mXTsMTB19rO5";

        List<String> genres = dataAccessObject.getGenres(artistId);

        for (String genre : genres) {
            System.out.println("Genre: " + genre);
            assertTrue("Elements of genres should be type String", genre instanceof String);
        }
    }


}