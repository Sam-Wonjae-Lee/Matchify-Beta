package interface_adapter.accept_invite;

import use_case.accept_invite.AcceptInputData;
import use_case.accept_invite.AcceptInteractor;

public class AcceptController {

    private final AcceptInteractor acceptInteractor;

    public AcceptController(AcceptInteractor acceptInteractor) {
        this.acceptInteractor = acceptInteractor;
    }

    public void execute(Integer user_id, Integer friend_id) {
        AcceptInputData acceptInputData = new AcceptInputData(user_id, friend_id);
        acceptInteractor.execute(acceptInputData);
    }
}
