package interface_adapter.send_invite;

import interface_adapter.match.MatchState;
import use_case.open_inbox.OpenInboxOutputData;
import use_case.send_invite.SendInviteOutputBoundary;
import use_case.send_invite.SendInviteOutputData;
import view.MatchView;

public class SendInvitePresenter implements SendInviteOutputBoundary {
    @Override
    public void prepareSuccessView(SendInviteOutputData user) {
    }
    //to be implemented if want a fail case later
    @Override
    public void prepareFailView(String error) {

    }
}
