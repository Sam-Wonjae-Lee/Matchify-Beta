package data_access;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Scanner;

public class AuthController {
    private static final int PORT = 8888;
    private static final String PATH = "/callback";
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8888/callback");
    private String code = "";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId("9ed5f6af048844e4851425fbc416ae10")
            .setClientSecret("df75314d40634c9db0d1da481a2302e8")
            .setRedirectUri(redirectUri)
            .build();

//    public String spotifyLogin() {
//        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//                .scope("user-read-private, user-read-email, user-top-read")
//                .show_dialog(true)
//                .build();
//
//        final URI uri = authorizationCodeUriRequest.execute();
//
//        return uri.toString();
//    }

    public String spotifyLogin() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext(PATH, new CallbackHandler());
            server.setExecutor(null);
            server.start();

            AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                    .scope("user-read-private,user-read-email,user-top-read")
                    .show_dialog(true)
                    .build();

            final URI uri = authorizationCodeUriRequest.execute();

            System.out.println("Please visit the following URL to authorize: " + uri);

            // Wait for the callback to receive the authorization code
            synchronized (this) {
                wait();
            }

            // Stop the server after receiving the authorization code
            server.stop(0);

            return code;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    static class CallbackHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] params = query.split("=");

            if (params.length == 2 && params[0].equals("code")) {
                AuthController authController = new AuthController();
                authController.setCode(params[1]);

                // Respond to the browser
                String response = "Authorization code received. You can close this window.";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }

                // Notify the waiting thread in the spotifyLogin method
                synchronized (authController) {
                    authController.notify();
                }
            }
        }
    }

    // Set the authorization code
    private void setCode(String code) {
        this.code = code;
    }

    public String getSpotifyUserCode(String userCode) throws IOException {
        code = userCode;
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();

        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return spotifyApi.getAccessToken();
    }

    public Artist[] getUserTopArtists() {
        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .time_range("medium_term")
                .limit(10)
                .offset(5)
                .build();

        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();

            return artistPaging.getItems();
        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }

        return new Artist[0];

    }

    public static void main(String[] args) {
        AuthController authController = new AuthController();

        String authorizationUri = authController.spotifyLogin();
        System.out.println("Authorization URI: " + authorizationUri);

        String userProvidedCode = authController.spotifyLogin();


        try {
            // Example: Get access token using the provided authorization code
            String accessToken = authController.getSpotifyUserCode(userProvidedCode);
            System.out.println("Access Token: " + accessToken);

            // Example: Get the user's top artists
            Artist[] topArtists = authController.getUserTopArtists();

            // Example: Print the names of the user's top artists
            System.out.println("User's Top Artists:");
            for (Artist artist : topArtists) {
                System.out.println(artist.getName());
            }

        } catch (IOException e) {
            System.out.println("Error during Spotify authentication: " + e.getMessage());
        }

    }

}
