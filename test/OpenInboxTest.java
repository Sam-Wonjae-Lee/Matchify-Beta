import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.open_inbox.OpenInboxPresenter;
import use_case.open_inbox.*;

import static org.junit.Assert.*;

public class OpenInboxTest {

    @org.junit.Test
    public void testSuccess(){
        String username = "testUser";
        OpenInboxInputData inputData = new OpenInboxInputData(username);
        OpenInboxUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();


        InboxViewModel inboxViewModel = null;
        ViewManagerModel viewManagerModel = null;
        OpenInboxOutputBoundary successPresenter = new OpenInboxPresenter(inboxViewModel, viewManagerModel){
            @Override
            public void prepareSuccessView(OpenInboxOutputData user){

                assertEquals(userRepository.get("testUser").getInbox(), user.getInbox());
                assertEquals("testUser", user.getUsername());
            }

        };

        OpenInboxInteractor interactor = new OpenInboxInteractor(userRepository, successPresenter);

        interactor.execute(inputData);


    }
}
