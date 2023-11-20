package view;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

public class ViewSimpleTest extends JFrame{

    private String CLIENT_ID = "YOUR_CLIENT_ID";
    private String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    //TODO: Set this in Spotify Dashboard
    private String REDIRECT_URI = "http://localhost:8888/callback";

    private SpotifyApi spotifyApi;

    public ViewSimpleTest() {
        super("Spotify Login");

        spotifyApi = new SpotifyApi.Builder()
                .setClientId(CLIENT_ID)
                .setClientSecret(CLIENT_SECRET)
                .setRedirectUri(REDIRECT_URI)
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
        } catch (InterruptedException | ExecutionException | SpotifyWebApiException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewSimpleTest().setVisible(true);
        });
    }

}
