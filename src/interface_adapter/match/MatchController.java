package interface_adapter.match;

import entity.CommonUser;
import use_case.match.MatchInputData;
import use_case.match.MatchInputboundary;

public class MatchController {

    final MatchInputboundary matchUseCaseInteractor;

    public MatchController(MatchInputboundary matchUseCaseInteractor) {
        this.matchUseCaseInteractor = matchUseCaseInteractor;
    }

    public void execute(String userID) {
        MatchInputData matchInputData = new MatchInputData(userID);
        this.matchUseCaseInteractor.execute(matchInputData);
    }


}
