package interface_adapter.decline_invite;

import use_case.decline_invite.DeclineInteractor;
import use_case.decline_invite.DeclineInputData;

public class DeclineController {

    private final DeclineInteractor declineInteractor;

    public DeclineController(DeclineInteractor declineInteractor) { this.declineInteractor= declineInteractor; }
    public void execute(String username) {
        DeclineInputData declineInputData = new DeclineInputData(username);
        declineInteractor.execute(declineInputData);
    }
}
