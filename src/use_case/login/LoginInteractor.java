package use_case.login;


import entity.FriendsList;
import entity.Inbox;
import entity.User;
import entity.UserFactory;

public class LoginInteractor implements LoginInputBoundary{

    final LoginUserDataAccessInterface userDataAccessObject;

    final LoginSpotifyAPIDataAccessInterface spotifyAPIDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    final UserFactory userFactory;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginSpotifyAPIDataAccessInterface spotifyAPIDataAccessObject,
                           LoginOutputBoundary loginPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.spotifyAPIDataAccessObject = spotifyAPIDataAccessObject;
        this.loginPresenter = loginPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String userId = loginInputData.getUserID();
        String name = spotifyAPIDataAccessObject.getName(userId);
        if(name == null){
            loginPresenter.prepareFailView("There is no account associated with "  + userId);
        }
        else {
            String pfp = spotifyAPIDataAccessObject.getProfilePicture(userId);
            if (!userDataAccessObject.userExists(userId)) {
                FriendsList lst = new FriendsList();
                Inbox inbox = new Inbox();
                User user = userFactory.create(name, lst, inbox);
                userDataAccessObject.save(user);
            }
            User user = userDataAccessObject.getUser(userId);
            LoginOutputData outputData = new LoginOutputData(userId, name, pfp, user.getFriendList(), false);
            loginPresenter.prepareSuccessView(outputData);
        }
    }
}
