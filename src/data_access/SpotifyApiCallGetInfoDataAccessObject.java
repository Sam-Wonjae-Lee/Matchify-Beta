package data_access;

import data_access.SpotifyApiCallUserProfileDataAccessObject;
import data_access.SpotifyApiCallUserPlaylistDataAccessObject;
import data_access.SpotifyApiCallPlaylistItemsDataAccessObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.json.JSONArray;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

public class SpotifyApiCallGetInfoDataAccessObject implements SpotifyApiCallInterface{

    // ========== User Profile DAO ==========
    public static String getUsername(String userId) throws IOException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        JSONObject userProfileResponse = SpotifyApiCallUserProfileDataAccessObject.getUserProfile(accessToken, userId);
        String username = userProfileResponse.getString("display_name");
        return username;
    }

    public static String getUserProfilePicture(String userId) throws IOException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        JSONObject userProfileResponse = SpotifyApiCallUserProfileDataAccessObject.getUserProfile(accessToken, userId);
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

    public static List<String> getPlaylistIds(String userId) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        List<String> playlistIds = new ArrayList<>();

        JSONObject response = SpotifyApiCallUserPlaylistDataAccessObject.getUserPlaylists(accessToken, userId);
        JSONArray playlists = response.getJSONArray("items");

        for (int i = 0; i < playlists.length(); i++) {
            JSONObject playlist = playlists.getJSONObject(i);
            String playlistId = playlist.getString("id");
            playlistIds.add(playlistId);
        }
        return playlistIds;
    }


    // ========== Playlist Items ==========

    public static List<String> getArtistsIds(String userId) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        List<String> artistIds = new ArrayList<>();

        JSONObject response = SpotifyApiCallPlaylistItemsDataAccessObject.getPlaylistItems(accessToken, userId);
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



    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException {
//        String username = getUsername("31x4bcq6wdza6wx7sh6pqks7u2em");
//        System.out.println(username);

//        String profileImage = getUserProfilePicture("31x4bcq6wdza6wx7sh6pqks7u2em");
//        System.out.println(profileImage);

//        List playlistIds = getPlaylistIds("ayimkorean");
//        System.out.println(playlistIds);

        List artistIds = getArtistsIds("3NQSAczjoIq6UXBps2pjW8");
        System.out.println(artistIds);

    }

}
