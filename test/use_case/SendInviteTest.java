package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.FriendsList;
import entity.Genre;
import entity.Inbox;
import interface_adapter.match.MatchViewModel;
import interface_adapter.send_invite.SendInvitePresenter;
import use_case.send_invite.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SendInviteTest {

    @org.junit.Test
    public void testSuccess(){
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        SendInviteInputData inputData = new SendInviteInputData("david", "frank");
        MatchViewModel matchViewModel = new MatchViewModel();

        FriendsList friendsList = new FriendsList();
        Inbox inbox = new Inbox();
        Genre genre = new Genre();
        CommonUser user1 = new CommonUser("david", friendsList, inbox, genre);
        userRepository.save(user1);
        SendInviteOutputBoundary successPresenter = new SendInvitePresenter(matchViewModel) {
            @Override
            public void prepareSuccessView(SendInviteOutputData user) {
                //assertTrue(userRepository.getUser("frank").getInbox().contains("david"));
            }
        };
        SendInviteInputBoundary interactor = new SendInviteInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
        }
}
