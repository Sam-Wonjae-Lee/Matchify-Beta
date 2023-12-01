package use_case.open_inbox;

import entity.User;

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
        User user = userDataAccessObject.get(openInboxInputData.getUsername());

        OpenInboxOutputData openInboxOutputData = new OpenInboxOutputData(user.getUserID(), user.getInbox());
        openInboxPresenter.prepareSuccessView(openInboxOutputData);
    }
}
