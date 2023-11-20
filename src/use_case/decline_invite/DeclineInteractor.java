package use_case.decline_invite;

public class DeclineInteractor {

    final DeclineUserDataAccessInterface userDataAccessObject;

    final DeclineOutputBoundary declinePresenter;

    public DeclineInteractor(DeclineUserDataAccessInterface userDataAccessObject, DeclineOutputBoundary declinePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.declinePresenter = declinePresenter;
    }

    public execute() { declinePresenter.prepareView(userDataAccessObject.del()); }
}
