package use_case;

import data_access.InMemoryUserDataAccessObject;
import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.match.MatchPresenter;
import interface_adapter.match.MatchViewModel;
import org.junit.Test;
import use_case.match.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class MatchTest {

    @Test
    void successTest() {
        String userID = "123";
        MatchUserAccessInterface userRepository = new InMemoryUserDataAccessObject();
        MatchSpotifyAccessInterface spotifyAccessInterface = new SpotifyApiCallGetInfoDataAccessObject();
//TODO: still need some work
        MatchOutputBoundary successPresenter = new MatchOutputBoundary() {
            @Override
            public void prepareSuccessView(MatchOutPutData userList) {
                System.out.println(userList.getClientUserID());
                List<String> listMatchedID = new ArrayList<>();
                List<String> listMatchedUsernames = new ArrayList<>();
                listMatchedID.add("abc123");
                listMatchedID.add("ghd555");
                listMatchedID.add("jjas123");
                listMatchedUsernames.add("Sam1");
                listMatchedUsernames.add("Frank21342");
                listMatchedUsernames.add("Andy12");
                assertEquals(userID, userList.getClientUserID());
                assertEquals(listMatchedID, listMatchedID);
                assertEquals(listMatchedUsernames, listMatchedUsernames);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unable to find Matches, please try again later.");
            }
        };

        MatchInputData inputData = new MatchInputData(userID);
        MatchInputboundary interactor = new MatchInteractor(successPresenter, userRepository, spotifyAccessInterface);
        interactor.execute(inputData);

    }
}
//void successTest() {
//SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//SignupOutputBoundary successPresenter = // Make a presenter here that asserts things
//SignupInputData inputData = new SignupInputData("Paul", "password", "password");
//SignupInputBoundary interactor = new SignupInteractor(
//userRepository, successPresenter, new CommonUserFactory());
//interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
//}
//17