package use_case.open_inbox;

import entity.User;

import java.util.HashMap;
import java.util.List;

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
        String user_id = openInboxInputData.getUser_id();
        User user = userDataAccessObject.getUser(user_id);
        HashMap<String, String> userToName = userDataAccessObject.getUsernameMap();


        OpenInboxOutputData openInboxOutputData = new OpenInboxOutputData(
                user_id,
                openInboxInputData.getUsername(),
                user.getInbox().get_invites(), userToName);
        openInboxPresenter.prepareSuccessView(openInboxOutputData);
    }
}
