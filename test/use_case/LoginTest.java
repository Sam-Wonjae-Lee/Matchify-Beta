package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.FriendsList;
import entity.Inbox;
import entity.CommonUser;
import entity.CommonUserFactory;
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


        ViewManagerModel viewManagerModel = null;
        HomePageViewModel homePageViewModel = null;
        LoginViewModel loginViewModel = null;
        LoginOutputBoundary successPresenter = new LoginPresenter(viewManagerModel, homePageViewModel, loginViewModel) {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertFalse(userRepository.userExists(userID));
                assertEquals("123", user.getUserID());
            }
        };
        // Requires mock spotify user data access object
        //LoginInteractor interactor = new LoginInteractor(userRepository,)
    }
}
