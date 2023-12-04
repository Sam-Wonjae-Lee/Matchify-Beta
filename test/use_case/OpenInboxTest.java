package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.FriendsList;
import entity.Genre;
import entity.Inbox;
import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.open_inbox.OpenInboxPresenter;
import use_case.open_inbox.*;

import static org.junit.Assert.*;

public class OpenInboxTest {


    //tests the correctness of output data for openInbox use case.
    @org.junit.Test
    public void testSuccess(){
        String username = "testUser";
        OpenInboxInputData inputData = new OpenInboxInputData(username, username);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        FriendsList friendsList = new FriendsList();
        Inbox inbox = new Inbox();
        Genre genre = new Genre();
        CommonUser user1 = new CommonUser(username, friendsList, inbox, genre);
        userRepository.save(user1);
        InboxViewModel inboxViewModel = new InboxViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        OpenInboxOutputBoundary successPresenter = new OpenInboxPresenter(inboxViewModel, viewManagerModel){
            @Override
            public void prepareSuccessView(OpenInboxOutputData user){
                assertEquals(userRepository.getUser("testUser").getInbox().get_invites(), user.getInbox());
                assertEquals("testUser", user.getUsername());
            }

        };

        OpenInboxInteractor interactor = new OpenInboxInteractor(userRepository, successPresenter);

        interactor.execute(inputData);


    }
}
