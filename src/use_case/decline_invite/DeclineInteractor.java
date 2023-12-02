package use_case.decline_invite;

public class DeclineInteractor implements DeclineInputBoundary{

    private final DeclineUserDataAccessInterface userDataAccessObject;
    private final DeclineOutputBoundary declinePresenter;

    public DeclineInteractor(DeclineUserDataAccessInterface userDataAccessObject, DeclineOutputBoundary declinePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.declinePresenter = declinePresenter;
    }

    public void execute(DeclineInputData inputData) {
        String username = inputData.getUsername();
        userDataAccessObject.deleteInvite(username);

        DeclineOutputData declineOutputData = new DeclineOutputData();
        declinePresenter.prepareView(declineOutputData);
    }
}
