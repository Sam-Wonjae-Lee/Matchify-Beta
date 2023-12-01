package interface_adapter.accept_invite;

import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.accept_invite.AcceptOutputBoundary;

public class AcceptPresenter implements AcceptOutputBoundary {

    private final InboxViewModel inboxViewModel;

    public AcceptPresenter(InboxViewModel inboxViewModel) { this.inboxViewModel = inboxViewModel; }

    @Override
    public void prepareView(String lst) {
        InboxState inboxState = inboxViewModel.getState();
        inboxState.setAdd(lst);
        inboxViewModel.firePropertyChanged();
    }
}