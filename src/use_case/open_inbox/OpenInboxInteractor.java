package use_case.open_inbox;

public class OpenInboxInteractor implements OpenInboxInputBoundary{
    final OpenInboxUserDataAccessInterface userDataAccessObject;

    final OpenInboxOutputBoundary openInboxPresenter;
    public OpenInboxInteractor(OpenInboxUserDataAccessInterface userDataAccessObject,
                               OpenInboxOutputBoundary openInboxPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.openInboxPresenter = openInboxPresenter;
    }

    @Override
    public void execute(OpenInboxInputData openInboxInputData) {
    }
}
