package view;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class LoginView extends JFrame{

    // ClientId, ClientSecret, RedirectURI - necessary info for using API.
    private String CLIENT_ID = "9ed5f6af048844e4851425fbc416ae10";
    private String CLIENT_SECRET = "df75314d40634c9db0d1da481a2302e8";
    private String REDIRECT_URI = "http://localhost:8888/callback";

    private SpotifyApi spotifyApi;

    public LoginView() {

        // JFrame window title
        super("Spotify Login");

        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(URI.create(REDIRECT_URI))
                .build();

        // Window will close to when clicking on X button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        setSize(300, 200);
        // Set centre of screen
        setLocationRelativeTo(null);

        // Button for login
        JButton loginButton = new JButton("Log in with Spotify");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSpotifyLoginWindow();
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(loginButton, BorderLayout.CENTER);
    }

    private void openSpotifyLoginWindow() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().build();
        URI uri = authorizationCodeUriRequest.executeAsync().join();

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String authorizationCode = JOptionPane.showInputDialog("Enter the authorization code: ");
        try {
            AuthorizationCodeCredentials authorizationCodeCredentials = spotifyApi.authorizationCode(authorizationCode).build().executeAsync().get();
            System.out.println("Access Token: " + authorizationCodeCredentials.getAccessToken());
            System.out.println("Refresh Token " + authorizationCodeCredentials.getRefreshToken());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves the access token. The access token is a string which contains the credentials and permissions that can be used to access resources.
     * The access token is valid for 1 hour. After that time, the token expires and you need to request a new one.
     * More info is located here: https://developer.spotify.com/documentation/web-api/concepts/access-token
     *
     * @return A string containing the temporary access token.
     * @throws Exception if access token cannot be retrieved.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }

}
