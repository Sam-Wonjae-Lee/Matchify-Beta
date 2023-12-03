package use_case.match;

import entity.CommonUser;
import entity.User;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface MatchUserAccessInterface {
    Collection<User> get_all_users();

    User getUser(String userID);
}
