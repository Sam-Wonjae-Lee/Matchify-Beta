package use_case.login;

import entity.User;

import java.util.List;

public interface LoginSpotifyAPIDataAccessInterface {

    // If user doesn't exist in csv file, we save it.
    // Otherwise, we don't
    String getName(String userID);

    String getProfilePicture(String userID);

    boolean userExists(String user_id);

    List<String> getGenres(String artistId);

    List<String> getPlaylistIds(String s);

    List<String> getArtistsIds(String playlistId);
}