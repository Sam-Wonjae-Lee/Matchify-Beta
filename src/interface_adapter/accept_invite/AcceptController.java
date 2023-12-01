package interface_adapter.accept_invite;

import use_case.accept_invite.AcceptInteractor;

public class AcceptController {

    final AcceptInteractor acceptInteractor;

    public AcceptController (AcceptInteractor acceptInteractor) { this.acceptInteractor = acceptInteractor; }
    public void execute() { acceptInteractor.execute(); }
}
