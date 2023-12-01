package use_case.login;

public class LoginInteractor implements LoginInputBoundary{

    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface, LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String userId = loginInputData.getUserId();
        if (userDataAccessObject.userExists(userId)) {
            // PrepareFailView refreshes SuccessesView
            loginPresenter.prepareFailView(userId + ": Account already exists.");
        } else {
            String username = userDataAccessObject.getUsername(userId);
            String profilePictureUrl = userDataAccessObject.getProfilePictureUrl(userId);
            LoginOutputData loginOutputData = new LoginOutputData(userId, username, profilePictureUrl, false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }
    }
}
