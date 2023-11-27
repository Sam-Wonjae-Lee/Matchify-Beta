package interface_adapter.Match;

import entity.CommonUser;
import use_case.Match.MatchInputData;
import use_case.Match.MatchInputobundary;

public class MatchController {

    MatchInputobundary matchUseCaseInteractor;

    MatchController(MatchInputobundary matchUseCaseInteractor) {
        this.matchUseCaseInteractor = matchUseCaseInteractor;
    }

    public void execute(CommonUser user) {
        MatchInputData matchInputData = new MatchInputData(user);
        this.matchUseCaseInteractor.execute(matchInputData);
    }


}
