package use_case.accept_invite;

public class AcceptInteractor {

    private final AcceptUserDataAccessInterface userDataAccessObject;
    private final AcceptOutputBoundary acceptPresenter;

    public AcceptInteractor(AcceptUserDataAccessInterface userDataAccessObject, AcceptOutputBoundary acceptPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.acceptPresenter = acceptPresenter;
    }

    public void execute(AcceptInputData inputData) {
        String username = inputData.getUsername();
        userDataAccessObject.accept(username);

        AcceptOutputData outputData = new AcceptOutputData();
        acceptPresenter.prepareView(outputData);
    }
}
