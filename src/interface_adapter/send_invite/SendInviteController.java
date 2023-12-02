package interface_adapter.send_invite;

import use_case.send_invite.SendInviteInputBoundary;
import use_case.send_invite.SendInviteInputData;

public class SendInviteController {

    final SendInviteInputBoundary sendInviteUseCaseInteractor;

    public SendInviteController(SendInviteInputBoundary sendInviteUseCaseInteractor) {
        this.sendInviteUseCaseInteractor = sendInviteUseCaseInteractor;
    }

    public void execute(String userID, String invitedID){
        SendInviteInputData sendInviteInputData = new SendInviteInputData(userID, invitedID);

        sendInviteUseCaseInteractor.execute(sendInviteInputData);
    }
}
