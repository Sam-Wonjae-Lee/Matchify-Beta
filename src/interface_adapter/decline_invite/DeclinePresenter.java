package interface_adapter.decline_invite;

import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.decline_invite.DeclineOutputBoundary;
import use_case.decline_invite.DeclineOutputData;

public class DeclinePresenter implements DeclineOutputBoundary {
    private final InboxViewModel inboxViewModel;
    public DeclinePresenter(InboxViewModel inboxViewModel) { this.inboxViewModel = inboxViewModel; }

    @Override
    public void prepareView(String user) {
        InboxState inboxState = inboxViewModel.getState();
        inboxState.setDel(user);
        inboxViewModel.firePropertyChanged();
    }

}
