package app;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

public class Main {
    public static void main(String[] args) {
        // Replace with your Spotify API credentials
        String clientId = "9ed5f6af048844e4851425fbc416ae10";
        String clientSecret = "df75314d40634c9db0d1da481a2302e8";

        // Create a Spotify API instance
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        // Set an access token (you may need to obtain one using the Authorization Code Flow)
        String accessToken = "BQBF0Y2penwR1rezm4csZWJCioSQufssze4NLhsNEuLYfUhA9kgktkeGXGhHENs9G9CyJalMrcEhIWz5cpD8_YZd065PymGFLseo-j1VbkGXAfkshuY";
        spotifyApi.setAccessToken(accessToken);

        // Specify the track ID you want to retrieve
        String trackId = "0vjeOZ3Ft5jvAi9SBFJm1j";

        // Create a GetTrackRequest and execute it
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
