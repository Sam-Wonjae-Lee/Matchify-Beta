package use_case;

import data_access.InMemorySpotifyDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.login.LoginController;
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
        LoginSpotifyAPIDataAccessInterface spotifyRepository = new InMemorySpotifyDataAccessObject();
        UserFactory userFactory = new CommonUserFactory();


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginOutputBoundary successPresenter = new LoginPresenter(viewManagerModel, homePageViewModel, loginViewModel) {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertFalse(userRepository.userExists(userID));
                assertEquals("123", user.getUserID());
            }
        };
        LoginInteractor interactor = new LoginInteractor(userRepository, spotifyRepository, successPresenter, userFactory);

        interactor.execute(inputData);
    }
}
