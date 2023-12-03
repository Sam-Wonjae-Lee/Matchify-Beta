package data_access;

import data_access.SpotifyApiCallUserProfileDataAccessObject;
import data_access.SpotifyApiCallUserPlaylistDataAccessObject;
import data_access.SpotifyApiCallPlaylistItemsDataAccessObject;
import data_access.SpotifyApiCallArtistGenresDataAccessObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import entity.CommonUser;
import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import org.json.JSONArray;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import use_case.login.LoginSpotifyAPIDataAccessInterface;
import use_case.match.MatchSpotifyAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;

public class SpotifyApiCallGetInfoDataAccessObject implements SpotifyApiCallInterface, LoginSpotifyAPIDataAccessInterface, MatchSpotifyAccessInterface{

    // ========== User Profile DAO ==========

    /**
     * Returns the Spotify username from Spotify user ID.
     * @param userId A String containing the Spotify user ID.
     * @return A String containing the Spotify username.
     * @throws IOException
     * */
    public String getUsername(String userId) throws IOException {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallUserProfileDataAccessObject userProfileDataAccessObject = new SpotifyApiCallUserProfileDataAccessObject();

        String accessToken = accessTokenDataAccessObject.getAccessToken();
        JSONObject userProfileResponse = userProfileDataAccessObject.getUserProfile(accessToken, userId);
        String username = userProfileResponse.getString("display_name");
        return username;
    }

    /**
     * Returns the URL to Spotify profile picture from Spotify user ID.
     * @param userId A String containing the Spotify user ID.
     * @return A URL containing the Spotify profile picture.
     * @throws IOException
     * */
    public String getUserProfilePicture(String userId) throws IOException {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallUserProfileDataAccessObject userProfileDataAccessObject = new SpotifyApiCallUserProfileDataAccessObject();

        String accessToken = accessTokenDataAccessObject.getAccessToken();
        JSONObject userProfileResponse = userProfileDataAccessObject.getUserProfile(accessToken, userId);
        JSONArray imagesArray = userProfileResponse.getJSONArray("images");

        if (imagesArray.length() >= 2) {
            JSONObject profileImage = imagesArray.getJSONObject(1);

            return profileImage.getString("url");
        } else {
            System.out.println("No profile image found.");
            return null;
        }

    }


    // ========== User Playlist DAO ==========

    /**
     * Returns list of playlist IDs from Spotify user ID.
     * @param userId A String containing the Spotify user ID.
     * @return A list containing the ID of each playlist that belongs to the Spotify user. */
    public List<String> getPlaylistIds(String userId) throws ExecutionException, InterruptedException {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallUserPlaylistDataAccessObject userPlaylistDataAccessObject = new SpotifyApiCallUserPlaylistDataAccessObject();

        String accessToken = accessTokenDataAccessObject.getAccessToken();
        List<String> playlistIds = new ArrayList<>();

        JSONObject response = userPlaylistDataAccessObject.getUserPlaylists(accessToken, userId);
        JSONArray playlists = response.getJSONArray("items");

        for (int i = 0; i < playlists.length(); i++) {
            JSONObject playlist = playlists.getJSONObject(i);
            String playlistId = playlist.getString("id");
            playlistIds.add(playlistId);
        }
        return playlistIds;
    }


    // ========== Playlist Items ==========

    /**
     * Returns list of artist IDs from Spotify playlist ID.
     * @param playlistId A String containing the Spotify playlist ID.
     * @return A list containing the ID of each artist that features in the Spotify playlist.
     * @throws IOException
     * */
    public List<String> getArtistsIds(String playlistId) throws ExecutionException, InterruptedException {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallPlaylistItemsDataAccessObject playlistItemsDataAccessObject = new SpotifyApiCallPlaylistItemsDataAccessObject();

        String accessToken = accessTokenDataAccessObject.getAccessToken();
        List<String> artistIds = new ArrayList<>();

        JSONObject response = playlistItemsDataAccessObject.getPlaylistItems(accessToken, playlistId);
        JSONArray itemsArray = response.getJSONArray("items");

        for (int i = 0; i< itemsArray.length(); i++) {
            JSONObject trackObject = itemsArray.getJSONObject(i);
            JSONObject track = trackObject.getJSONObject("track");

            JSONArray artistsArray = track.getJSONArray("artists");

            for (int j = 0; j < artistsArray.length(); j++) {
                JSONObject artist = artistsArray.getJSONObject(j);
                String artistId = artist.getString("id");
                artistIds.add(artistId);
            }

        }
        return artistIds;
    }


    // ========== Artist Genres ==========

    /**
     * Returns list of artist IDs from Spotify playlist ID.
     * @param artistId A String containing the Spotify artist ID.
     * @return A list containing all the genres from the Spotify artist. */
    public List<String> getGenres(String artistId) throws IOException, ParseException, SpotifyWebApiException {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallArtistGenresDataAccessObject artistGenresDataAccessObject = new SpotifyApiCallArtistGenresDataAccessObject();

        String accessToken = accessTokenDataAccessObject.getAccessToken();
        List<String> genres = new ArrayList<>();

        JSONObject response = artistGenresDataAccessObject.getArtistGenres(accessToken, artistId);
        JSONArray genresArray = response.getJSONArray("genres");

        for (int i = 0; i < genresArray.length(); i++) {
            String genre = genresArray.getString(i);
            genres.add(genre);
        }
        return genres;
    }

    @Override
    public boolean userExists(String userId) throws IOException, ParseException, SpotifyWebApiException {
        SpotifyApiCallAccessTokenDataAccessObject accessTokenDataAccessObject = new SpotifyApiCallAccessTokenDataAccessObject();
        SpotifyApiCallUserProfileDataAccessObject dataAccessObject = new SpotifyApiCallUserProfileDataAccessObject();
        if (dataAccessObject.checkUserExists(accessTokenDataAccessObject.getAccessToken(),userId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName(String userID) throws IOException {
        return getUsername(userID);
    }

    @Override
    public String getProfilePicture(String userID) {
        return getProfilePicture(userID);
    }


}
