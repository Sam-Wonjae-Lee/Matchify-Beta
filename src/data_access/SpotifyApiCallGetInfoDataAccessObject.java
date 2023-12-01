package data_access;

import data_access.SpotifyApiCallUserProfileDataAccessObject;
import data_access.SpotifyApiCallUserPlaylistDataAccessObject;
import data_access.SpotifyApiCallPlaylistItemsDataAccessObject;
import data_access.SpotifyApiCallArtistGenresDataAccessObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import org.apache.hc.core5.http.ParseException;
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

    public static List<String> getArtistsIds(String playlistId) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        List<String> artistIds = new ArrayList<>();

        JSONObject response = SpotifyApiCallPlaylistItemsDataAccessObject.getPlaylistItems(accessToken, playlistId);
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

    public static List<String> getGenres(String artistId) throws IOException, ParseException, ExecutionException, InterruptedException, SpotifyWebApiException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        List<String> genres = new ArrayList<>();

        JSONObject response = SpotifyApiCallArtistGenresDataAccessObject.getArtistGenres(accessToken, artistId);
        JSONArray genresArray = response.getJSONArray("genres");

        for (int i = 0; i < genresArray.length(); i++) {
            String genre = genresArray.getString(i);
            genres.add(genre);
        }
        return genres;
    }


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException, ParseException {


        Map<String, Integer> allGenresFrequencyMap = new HashMap<>();

        List<String> playlistIds = getPlaylistIds("31s453ebjxsfte4fzcyqkanmrlb4");
        for (String playlistId : playlistIds) {
            List<String> artistIds = getArtistsIds(playlistId);

            for (String artistId : artistIds) {
                List<String> genres = getGenres(artistId);

                for (String genre : genres) {
                    allGenresFrequencyMap.put(genre, allGenresFrequencyMap.getOrDefault(genre, 0) + 1);
                }
            }

        }
        System.out.println(allGenresFrequencyMap);


    }

}
