package interface_adapter.open_inbox;

import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;
import use_case.open_inbox.OpenInboxOutputBoundary;
import use_case.open_inbox.OpenInboxOutputData;

public class OpenInboxPresenter implements OpenInboxOutputBoundary {

    private final InboxViewModel inboxViewModel;

    private final ViewManagerModel viewManagerModel;

    public OpenInboxPresenter(InboxViewModel inboxViewModel, ViewManagerModel viewManagerModel) {
        this.inboxViewModel = inboxViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(OpenInboxOutputData response) {
        InboxState inboxState = inboxViewModel.getState();
        inboxState.setUser_id(response.getUser_id());
        inboxState.setUsername(response.getUsername());
        inboxState.setInbox(response.getInbox());
        this.inboxViewModel.setState(inboxState);
        this.inboxViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(inboxViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
