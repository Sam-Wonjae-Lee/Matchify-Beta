package use_case.match;

import entity.CommonUser;

import java.util.List;

public interface MatchSpotifyAccessInterface {
    List<String> getGenres(String artistId);

    List<String> getPlaylistIds(String s);

    List<String> getArtistsIds(String playlistId);

    CommonUser getUser(String userID);
}
