package interface_adapter.match;

import entity.CommonUser;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import use_case.match.MatchInputData;
import use_case.match.MatchInputboundary;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MatchController {

    MatchInputboundary matchUseCaseInteractor;

    public MatchController(MatchInputboundary matchUseCaseInteractor) {
        this.matchUseCaseInteractor = matchUseCaseInteractor;
    }

    public void execute(String user) {
        MatchInputData matchInputData = new MatchInputData(user);
        this.matchUseCaseInteractor.execute(matchInputData);
    }


}
