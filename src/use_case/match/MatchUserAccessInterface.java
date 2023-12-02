package use_case.match;

import entity.User;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface MatchUserAccessInterface {
    Set<String> get_all_users();
}
