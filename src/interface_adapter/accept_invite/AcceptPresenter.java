package interface_adapter.accept_invite;

import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.accept_invite.AcceptOutputBoundary;
import use_case.accept_invite.AcceptOutputData;

public class AcceptPresenter implements AcceptOutputBoundary {

    @Override
    public void prepareView(AcceptOutputData acceptOutputData) {
    }
}