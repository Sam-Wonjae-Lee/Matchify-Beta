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

public class SpotifyApiCallArtistGenresDataAccessObject implements SpotifyApiCallInterface{

    private static final String CLIENT_ID = SpotifyApiCallInterface.CLIENT_ID;
    private static final String CLIENT_SECRET = SpotifyApiCallInterface.CLIENT_SECRET;
    private static final String REDIRECT_URI = SpotifyApiCallInterface.REDIRECT_URI;


    /**
     * Get the information of the artist (which includes the associated genres with the artists) from given artist ID.
     * More info is located here: https://developer.spotify.com/documentation/web-api/reference/get-an-artist
     *
     * @param accessToken A string containing the temporary access token.
     * @param artistId A string containing the Spotify artist ID.
     * @return A JSONObject containing the response for the artist.
     * */
    private static JSONObject getArtistGenres(String accessToken, String artistId)
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();

        System.out.println("Enter Artist ID: ");
        String artistId = scanner.nextLine();
        try {
            JSONObject artistJson = getArtistGenres(accessToken, artistId);
            System.out.println(artistJson);
        } catch (IOException | SpotifyWebApiException | ParseException | ExecutionException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
