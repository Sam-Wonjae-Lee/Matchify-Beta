package use_case.decline_invite;

public class DeclineInteractor {

    final DeclineUserDataAccessInterface userDataAccessObject;

    final DeclineOutputBoundary declinePresenter;

    public DeclineInteractor(DeclineUserDataAccessInterface userDataAccessObject, DeclineOutputBoundary declinePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.declinePresenter = declinePresenter;
    }

    public void execute() { declinePresenter.prepareView(userDataAccessObject.delete()); }
}
