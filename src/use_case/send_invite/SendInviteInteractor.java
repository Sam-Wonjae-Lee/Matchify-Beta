package use_case.send_invite;

public class SendInviteInteractor implements SendInviteInputBoundary{

    final SendInviteDataAccessInterface userDataAccessObject;

    final SendInviteOutputBoundary userPresenter;

    public SendInviteInteractor(SendInviteDataAccessInterface userDataAccessObject,
                                SendInviteOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SendInviteInputData sendInviteInputData) {
        userDataAccessObject.addToInbox(sendInviteInputData.getUserID(),
                                        sendInviteInputData.getInvitedUserID());
        SendInviteOutputData sendInviteOutputData = new SendInviteOutputData(true);
        userPresenter.prepareSuccessView(sendInviteOutputData);
    }
}
