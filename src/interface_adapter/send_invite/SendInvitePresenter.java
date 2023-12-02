package interface_adapter.send_invite;

import use_case.open_inbox.OpenInboxOutputData;
import use_case.send_invite.SendInviteOutputBoundary;
import use_case.send_invite.SendInviteOutputData;

public abstract class SendInvitePresenter implements SendInviteOutputBoundary {
    @Override
    public void prepareSuccessView(SendInviteOutputData user) {

    }
    //to be implemented if want a fail case later
    @Override
    public void prepareFailView(String error) {

    }
}
