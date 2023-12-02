package data_access;

import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/*
 * SpotifyApiCallArtistGenresDataAccessObject contains the function to retrieve the "get artist" response.
 * */

public class SpotifyApiCallArtistGenresDataAccessObject implements SpotifyApiCallInterface{

    private final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;


    /**
     * Get the information of the artist (which includes the associated genres with the artists) from given artist ID.
     * More info is located here: https://developer.spotify.com/documentation/web-api/reference/get-an-artist
     *
     * @param accessToken A string containing the temporary access token.
     * @param artistId A string containing the Spotify artist ID.
     * @return A JSONObject containing the response for the artist.
     * Example Response:
     * {"images":[{"width":640,"url":"https://i.scdn.co/image/ab6761610000e5ebadd503b411a712e277895c8a","height":640},{"width":320,"url":"https://i.scdn.co/image/ab67616100005174add503b411a712e277895c8a","height":320},
     * {"width":160,"url":"https://i.scdn.co/image/ab6761610000f178add503b411a712e277895c8a","height":160}],"followers":{"total":22057537},"genres":["conscious hip hop","hip hop","north carolina hip hop","rap"],
     * "popularity":84,"name":"J. Cole","id":"6l3HvQ5sa6mXTsMTB19rO5","externalUrls":{"externalUrls":{"spotify":"https://open.spotify.com/artist/6l3HvQ5sa6mXTsMTB19rO5"}},
     * "href":"https://api.spotify.com/v1/artists/6l3HvQ5sa6mXTsMTB19rO5","type":"ARTIST","uri":"spotify:artist:6l3HvQ5sa6mXTsMTB19rO5"}
     * */
    public JSONObject getArtistGenres(String accessToken, String artistId)
            throws IOException, SpotifyWebApiException, InterruptedException, ExecutionException, ParseException {

        // Initialize the Spotify API object
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(URI.create(REDIRECT_URI))
                .setAccessToken(accessToken)
                .build();

        GetArtistRequest request = spotifyApi.getArtist(artistId).build();

        Artist artist = request.execute();

        JSONObject artistJson = new JSONObject(artist);

        return artistJson;

    }


}
