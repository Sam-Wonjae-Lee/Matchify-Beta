package interface_adapter.accept_invite;

import use_case.accept_invite.AcceptInputData;
import use_case.accept_invite.AcceptInteractor;

public class AcceptController {

    private final AcceptInteractor acceptInteractor;

    public AcceptController(AcceptInteractor acceptInteractor) {
        this.acceptInteractor = acceptInteractor;
    }

    public void execute(String username) {
        AcceptInputData acceptInputData = new AcceptInputData(username);
        acceptInteractor.execute(acceptInputData);
    }
}
