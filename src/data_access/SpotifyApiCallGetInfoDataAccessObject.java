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
import use_case.match.MatchDataAccessInterface;

public class SpotifyApiCallGetInfoDataAccessObject implements SpotifyApiCallInterface, MatchDataAccessInterface {

    // ========== User Profile DAO ==========

    /**
     * Returns the Spotify username from Spotify user ID.
     * @param userId A String containing the Spotify user ID.
     * @return A String containing the Spotify username.
     * @throws IOException
     * */
    public static String getUsername(String userId) throws IOException {
        String accessToken = SpotifyApiCallAccessTokenDataAccessObject.getAccessToken();
        JSONObject userProfileResponse = SpotifyApiCallUserProfileDataAccessObject.getUserProfile(accessToken, userId);
        String username = userProfileResponse.getString("display_name");
        return username;
    }

    /**
     * Returns the URL to Spotify profile picture from Spotify user ID.
     * @param userId A String containing the Spotify user ID.
     * @return A URL containing the Spotify profile picture.
     * @throws IOException
     * */
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

    /**
     * Returns list of playlist IDs from Spotify user ID.
     * @param userId A String containing the Spotify user ID.
     * @return A list containing the ID of each playlist that belongs to the Spotify user.
     * @throws IOException
     * */
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

    /**
     * Returns list of artist IDs from Spotify playlist ID.
     * @param playlistId A String containing the Spotify playlist ID.
     * @return A list containing the ID of each artist that features in the Spotify playlist.
     * @throws IOException
     * */
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

    /**
     * Returns list of artist IDs from Spotify playlist ID.
     * @param artistId A String containing the Spotify artist ID.
     * @return A list containing all the genres from the Spotify artist.
     * @throws IOException
     * */
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


    /* This is example code */
//    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException, ParseException {
//
//
//        /*
//        * This code contains an example of returning a hashmap where the key is the genre and the value is the frequency.
//        * */
//        Map<String, Integer> allGenresFrequencyMap = new HashMap<>();
//
//        // getPlaylistIds contains a Spotify user ID
//        List<String> playlistIds = getPlaylistIds("31s453ebjxsfte4fzcyqkanmrlb4");
//        for (String playlistId : playlistIds) {
//            List<String> artistIds = getArtistsIds(playlistId);
//
//            for (String artistId : artistIds) {
//                List<String> genres = getGenres(artistId);
//
//                for (String genre : genres) {
//                    allGenresFrequencyMap.put(genre, allGenresFrequencyMap.getOrDefault(genre, 0) + 1);
//                }
//            }
//
//        }
//        System.out.println(allGenresFrequencyMap);
//
//        /* Example Output:
//        * {heartland rock=1, japanese chillhop=4, small room=1, singer-songwriter=1, lo-fi house=1,
//        * piano rock=1, indietronica=1, pop rap=1, classic rock=1, modern alternative pop=1, mellow gold=1,
//        * lo-fi rap=20, pittsburgh rap=1, indie rock=1, soft rock=1, rap=2, post-teen pop=2, art pop=1, irish pop=1,
//        * lo-fi emo=2, neo mellow=1, modern rock=1, sad lo-fi=47, show tunes=3, lo-fi indie=3, canadian contemporary r&b=1,
//        * bubblegrunge=1, indonesian indie=1, chillhop=2, pov: indie=36, alt z=3, pop=2, rock=1, lo-fi chill=48, sad rap=3, hip hop=2,
//        * bedroom pop=18, lo-fi beats=11, pixel=1, album rock=1, indie pop=13, meme rap=4, future funk=1, canadian pop=1, aesthetic rap=1,
//        * dance pop=1, otacore=1, future bass=1, brooklyn indie=1, indie psych-pop=2, experimental pop=1, slowed and reverb=1, comic=10}
//        * */
//
//    }

    @Override
    public List<String> getUserPlaylistID(String user) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException {
        return getPlaylistIds(user);
    }
}
