package interface_adapter.decline_invite;

import use_case.decline_invite.DeclineInputBoundary;
import use_case.decline_invite.DeclineInteractor;
import use_case.decline_invite.DeclineInputData;


public class DeclineController {

    private final DeclineInputBoundary declineInteractor;

    public DeclineController(DeclineInputBoundary declineInteractor) { this.declineInteractor= declineInteractor; }
  
    public void execute(String user_id, String inviter_id) {
        DeclineInputData declineInputData = new DeclineInputData(user_id, inviter_id);
        declineInteractor.execute(declineInputData);
    }
}
