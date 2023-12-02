package interface_adapter.match;

import entity.CommonUser;
import use_case.match.MatchInputData;
import use_case.match.MatchInputobundary;

public class MatchController {

    MatchInputobundary matchUseCaseInteractor;

    public MatchController(MatchInputobundary matchUseCaseInteractor) {
        this.matchUseCaseInteractor = matchUseCaseInteractor;
    }

    public void execute(String user) {
        MatchInputData matchInputData = new MatchInputData(user);
        this.matchUseCaseInteractor.execute(matchInputData);
    }


}
