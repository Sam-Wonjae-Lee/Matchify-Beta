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
        String friend_id = inputData.getFriend_id();
        userDataAccessObject.deleteInvite(username, friend_id);

        DeclineOutputData declineOutputData = new DeclineOutputData();
        declinePresenter.prepareView(declineOutputData);
    }
}
