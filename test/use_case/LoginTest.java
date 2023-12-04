package use_case;

import data_access.InMemorySpotifyDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.accept_invite.AcceptPresenter;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;
import org.junit.Assert;
import use_case.accept_invite.AcceptOutputBoundary;
import use_case.login.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    // Tests the correctness of output data for login use case.
    @org.junit.Test
    public void testSuccess() {
        String userID = "123";
        LoginInputData inputData = new LoginInputData(userID);
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        InMemorySpotifyDataAccessObject spotifyRepository = new InMemorySpotifyDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginOutputBoundary successPresenter = new LoginPresenter(viewManagerModel, homePageViewModel, loginViewModel) {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertFalse(userRepository.userExists(userID));
                assertEquals(userID, user.getUserID());
            }
        };
        spotifyRepository.add_playlist(userID,"411");
        LoginInteractor interactor = new LoginInteractor(userRepository, spotifyRepository, successPresenter, userFactory);
        interactor.map_playlist(userID);
        interactor.execute(inputData);
    }

    @org.junit.Test
    public void testFail() {
        String userID = "giughiuh-oshv-=aodeibfhv";
        LoginInputData inputData = new LoginInputData(userID);
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        InMemorySpotifyDataAccessObject spotifyRepository = new InMemorySpotifyDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginOutputBoundary successPresenter = new LoginPresenter(viewManagerModel, homePageViewModel, loginViewModel) {
            @Override
            public void prepareFailView(String error) {
                assertFalse(userRepository.userExists("Beu"));
            }
        };
        spotifyRepository.add_playlist(userID,"411");
        System.out.println(spotifyRepository.getPlaylistIds(userID));
        LoginInteractor interactor = new LoginInteractor(userRepository, spotifyRepository, successPresenter, userFactory);
        interactor.map_playlist(userID);
        interactor.execute(inputData);
    }


}
