import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.open_inbox.OpenInboxPresenter;
import use_case.open_inbox.OpenInboxInputData;
import use_case.open_inbox.OpenInboxInteractor;
import use_case.open_inbox.OpenInboxOutputBoundary;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;

import static org.junit.Assert.*;

public class OpenInboxTest {

    @org.junit.Test
    public void testSuccess(){
        String username = "testUser";
        OpenInboxInputData inputData = new OpenInboxInputData(username);
        OpenInboxUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();


        InboxViewModel inboxViewModel = null;
        ViewManagerModel viewManagerModel = null;
        OpenInboxOutputBoundary successPresenter = new OpenInboxPresenter(inboxViewModel, viewManagerModel);

        OpenInboxInteractor interactor = new OpenInboxInteractor(userDataAccessObject, successPresenter);

        interactor.execute(inputData);
    }
}
