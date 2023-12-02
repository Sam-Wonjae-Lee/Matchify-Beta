package interface_adapter.login;

//import interface_adapter.logged_in.LoggedInState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;
    private final HomePageViewModel homePageViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomePageViewModel homePageViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homePageViewModel = homePageViewModel;
        this.loginViewModel = loginViewModel;
    }


    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the home page view.

    }


    @Override
    public void prepareFailView(String error) {

    }

}
