package interface_adapter.decline_invite;

import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.decline_invite.DeclineOutputBoundary;
import use_case.decline_invite.DeclineOutputData;

import java.util.List;

public class DeclinePresenter implements DeclineOutputBoundary {

    private final InboxViewModel inboxViewModel;

    public DeclinePresenter(InboxViewModel inboxViewModel) {
        this.inboxViewModel = inboxViewModel;
    }

    @Override
    public void prepareView(DeclineOutputData declineOutputData) {
        InboxState inboxState = inboxViewModel.getState();
        List<String> inbox = inboxState.getInbox();
        inbox.remove(declineOutputData.getDeletedInvite());
        inboxState.setInbox(inbox);

        this.inboxViewModel.setState(inboxState);
        inboxViewModel.firePropertyChanged();
    }
}
