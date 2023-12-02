package use_case.match;

import entity.CommonUser;
import entity.User;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MatchDataAccessInterface {
    void save(User user);

    List<String> getUserPlaylistID(String user) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException;
}
