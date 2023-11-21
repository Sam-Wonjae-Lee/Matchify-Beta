package data_access;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetUsersProfileRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersProfileExample {
    private static final String accessToken = "BQDpQxBR4cYQepKyNL-Fup4jIHrejKN5JiGbkqJoJwr1GDjJ1TduDS1JaDRkAlPkn84aGTsfCBj3iOZEqo3SJgK3_IG43dzMEsSbgcFPDTtUxO1CGiA";
    private static final String userId = "user_id";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetUsersProfileRequest getUsersProfileRequest = spotifyApi.getUsersProfile(userId)
            .build();

    public static void getUsersProfile_Sync() {
        try {
            final User user = getUsersProfileRequest.execute();

            System.out.println("Display name: " + user.getDisplayName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getUsersProfile_Async() {
        try {
            final CompletableFuture<User> userFuture = getUsersProfileRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final User user = userFuture.join();

            System.out.println("Display name: " + user.getDisplayName());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        getUsersProfile_Sync();
        getUsersProfile_Async();
    }
}