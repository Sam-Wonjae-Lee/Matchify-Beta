package interface_adapter.decline_invite;

import use_case.decline_invite.DeclineInteractor;

public class DeclineController {

    final DeclineInteractor declineInteractor;

    public DeclineController(DeclineInteractor declineInteractor) { this.declineInteractor= declineInteractor; }
    public void execute() { declineInteractor.execute(); }
}
