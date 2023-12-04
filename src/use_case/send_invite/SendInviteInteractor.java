package use_case.send_invite;

public class SendInviteInteractor implements SendInviteInputBoundary{

    final SendInviteUserDataAccessInterface userDataAccessObject;

    final SendInviteOutputBoundary userPresenter;

    public SendInviteInteractor(SendInviteUserDataAccessInterface userDataAccessObject,
                                SendInviteOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(SendInviteInputData sendInviteInputData) {
        userDataAccessObject.addToInbox(sendInviteInputData.getUserID(),
                                        sendInviteInputData.getInvitedUserID());
        SendInviteOutputData sendInviteOutputData = new SendInviteOutputData(sendInviteInputData.getInvitedUserID());
        userPresenter.prepareSuccessView(sendInviteOutputData);
    }
}
