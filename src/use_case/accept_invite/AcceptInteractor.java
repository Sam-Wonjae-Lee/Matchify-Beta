package use_case.accept_invite;

public class AcceptInteractor implements AcceptInputBoundary{

    private final AcceptUserDataAccessInterface userDataAccessObject;
    private final AcceptOutputBoundary acceptPresenter;

    public AcceptInteractor(AcceptUserDataAccessInterface userDataAccessObject, AcceptOutputBoundary acceptPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.acceptPresenter = acceptPresenter;
    }

    public void execute(AcceptInputData inputData) {
         String user_id = inputData.getUserId();
         String friend_id = inputData.getFriendId();
        userDataAccessObject.add_friend(user_id, friend_id);

        AcceptOutputData outputData = new AcceptOutputData(friend_id);
        acceptPresenter.prepareView(outputData);
    }
}
