package use_case;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.accept_invite.AcceptPresenter;
import use_case.accept_invite.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class AcceptTest {

    @org.junit.Test
    public void testSuccess() {
        String userID = "123";
        String friendID = "345";
        FriendsList friendsList = new FriendsList();
        Inbox inbox = new Inbox();
        Genre genre = new Genre();
        CommonUser user1 = new CommonUser(userID, friendsList, inbox, genre);
        CommonUser user2 = new CommonUser(friendID, friendsList, inbox, genre);
        AcceptInputData inputData = new AcceptInputData(userID, friendID);
        InboxViewModel inboxViewModel = new InboxViewModel();
        InMemoryUserDataAccessObject acceptUserDataAccessInterface = new InMemoryUserDataAccessObject();
        acceptUserDataAccessInterface.save(user1);
        acceptUserDataAccessInterface.save(user2);
        acceptUserDataAccessInterface.add_friend("123","345");
        AcceptOutputBoundary successPresenter = new AcceptPresenter(inboxViewModel);
        AcceptInteractor interactor = new AcceptInteractor(acceptUserDataAccessInterface, successPresenter);

        interactor.execute(inputData);
    }
}
