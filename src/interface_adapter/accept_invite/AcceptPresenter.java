package interface_adapter.accept_invite;

import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.accept_invite.AcceptOutputBoundary;
import use_case.accept_invite.AcceptOutputData;

import java.util.List;

public class AcceptPresenter implements AcceptOutputBoundary {

    private final InboxViewModel inboxViewModel;

    public AcceptPresenter(InboxViewModel inboxViewModel) {
        this.inboxViewModel = inboxViewModel;
    }

    @Override
    public void prepareView(AcceptOutputData acceptOutputData) {
        InboxState inboxState = inboxViewModel.getState();
        List<String> inbox = inboxState.getInbox();
        inbox.remove(acceptOutputData.getDeletedInvite());
        inboxState.setInbox(inbox);

        this.inboxViewModel.setState(inboxState);
        inboxViewModel.firePropertyChanged();
    }
}