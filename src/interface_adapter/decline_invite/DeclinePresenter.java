package interface_adapter.decline_invite;

import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.decline_invite.DeclineOutputBoundary;
import use_case.decline_invite.DeclineOutputData;

import javax.swing.text.View;

public class DeclinePresenter implements DeclineOutputBoundary {

    @Override
    public void prepareView(DeclineOutputData declineOutputData) {
    }
}
