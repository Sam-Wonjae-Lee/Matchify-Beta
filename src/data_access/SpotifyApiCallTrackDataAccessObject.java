package data_access;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

import java.util.Scanner;

// This is just test code for getting information about a Spotify track from the track ID.

public class SpotifyApiCallTrackDataAccessObject implements SpotifyApiCallInterface{
    static String clientId = SpotifyApiCallInterface.CLIENT_ID;
    static String clientSecret = SpotifyApiCallInterface.CLIENT_SECRET;

    public static void main(String[] args) {

        // Create a Spotify API instance
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        Scanner scanner = new Scanner(System.in);

        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();

        System.out.println("Enter track Id: ");
        String trackId = scanner.nextLine();

        spotifyApi.setAccessToken(accessToken);

        GetTrackRequest trackRequest = spotifyApi.getTrack(trackId).build();
        try {
            Track track = trackRequest.execute();
            System.out.println("Track Name: " + track.getName());
            System.out.println("Artist: " + track.getArtists()[0].getName());
            System.out.println("Album: " + track.getAlbum().getName());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
