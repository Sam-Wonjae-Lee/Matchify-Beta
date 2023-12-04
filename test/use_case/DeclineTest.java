package use_case;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.decline_invite.DeclinePresenter;
import use_case.decline_invite.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeclineTest {

    @org.junit.Test
    public void testSuccess() {
        String userID = "123";
        String friendID = "345";
        DeclineInputData inputData = new DeclineInputData(userID, friendID);
        DeclineUserDataAccessInterface declineUserDataAccessInterface = new InMemoryUserDataAccessObject();

        InboxViewModel inboxViewModel = new InboxViewModel();

        DeclineOutputBoundary successPresenter = new DeclinePresenter(inboxViewModel);
        DeclineInteractor interactor = new DeclineInteractor(declineUserDataAccessInterface, successPresenter);

        interactor.execute(inputData);
    }
}
