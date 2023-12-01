package interface_adapter.accept_invite;

import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.accept_invite.AcceptOutputBoundary;
import use_case.accept_invite.AcceptOutputData;

public class AcceptPresenter implements AcceptOutputBoundary {

    private final InboxViewModel inboxViewModel;

    public AcceptPresenter(InboxViewModel inboxViewModel) { this.inboxViewModel = inboxViewModel; }

    @Override
    public void prepareView(String user) {
        InboxState inboxState = inboxViewModel.getState();
        inboxState.setDel(user);
        inboxViewModel.firePropertyChanged();
    }
}