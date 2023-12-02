package use_case.match;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MatchInputobundary {
    void execute(MatchInputData matchInputData);
}
