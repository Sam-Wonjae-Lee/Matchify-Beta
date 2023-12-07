package use_case;

import data_access.InMemoryUserDataAccessObject;
import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.CommonUser;
import entity.FriendsList;
import entity.Genre;
import entity.Inbox;
import use_case.home_page.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class HomePageTest {
    @Test
    public void successTest() {
        HomePageUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        FriendsList friendsList = new FriendsList();
        String username = "testUser";
        Inbox inbox = new Inbox();
        Genre genre = new Genre();
        CommonUser user1 = new CommonUser(username, friendsList, inbox, genre);
        HomePageOutPutBoundary successPresenter = new HomePageOutPutBoundary() {
            @Override
            public void prepareSuccessView(HomePageOutputData outputData) {
                assertEquals(outputData.getUserID(), "123");

            }
        };
        HomePageInputData inputData = new HomePageInputData("123");
        HomePageInputBoundary interactor = new HomePageInteractor(successPresenter, new SpotifyApiCallGetInfoDataAccessObject(), userRepository);

        interactor.execute(inputData);
    }

}
