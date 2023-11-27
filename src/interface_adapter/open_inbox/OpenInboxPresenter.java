package interface_adapter.open_inbox;

import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxViewModel;
import use_case.open_inbox.OpenInboxOutputBoundary;
public class OpenInboxPresenter implements OpenInboxOutputBoundary {

    private final InboxViewModel inboxViewModel;

    private final ViewManagerModel viewManagerModel;

    public OpenInboxPresenter(InboxViewModel inboxViewModel, ViewManagerModel viewManagerModel) {
        this.inboxViewModel = inboxViewModel;
        this.viewManagerModel = viewManagerModel;
    }
}
