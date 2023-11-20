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

    private String CLIENT_ID = "9ed5f6af048844e4851425fbc416ae10";
    private String CLIENT_SECRET = "df75314d40634c9db0d1da481a2302e8";
    private String REDIRECT_URI = "http://localhost:8888/callback";

    private SpotifyApi spotifyApi;

    public LoginView() {
        super("Spotify Login");

        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(URI.create(REDIRECT_URI))
                .build();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }

}
