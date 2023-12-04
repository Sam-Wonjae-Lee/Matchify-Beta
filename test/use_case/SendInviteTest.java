package use_case;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.match.MatchViewModel;
import interface_adapter.send_invite.SendInvitePresenter;
import use_case.send_invite.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SendInviteTest {

    @org.junit.Test
    public void testSuccess(){
        SendInviteUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        SendInviteInputData inputData = new SendInviteInputData("david", "frank");
        MatchViewModel matchViewModel = new MatchViewModel();
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
