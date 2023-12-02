package use_case.match;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MatchInputboundary {
    void execute(MatchInputData matchInputData);
}
