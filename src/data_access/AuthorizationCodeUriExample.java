package data_access;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.io.IOException;

public class AuthorizationCodeUriExample {
  private static final String clientId = "9ed5f6af048844e4851425fbc416ae10";
  private static final String clientSecret = "df75314d40634c9db0d1da481a2302e8";
  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8888/callback");

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();
  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
          .scope("user-library-read user-top-read")
          .show_dialog(true)
          .build();

  public static void authorizationCodeUri_Sync() {
    final URI uri = authorizationCodeUriRequest.execute();

    System.out.println("URI: " + uri.toString());
  }

  public static void authorizationCodeUri_Async() {
    try {
      final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final URI uri = uriFuture.join();

      System.out.println("URI: " + uri.toString());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    authorizationCodeUri_Sync();
    authorizationCodeUri_Async();

  }
}
