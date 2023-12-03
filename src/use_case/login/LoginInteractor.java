package use_case.login;


import data_access.FileUserDataAccessObject;
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
        System.out.println(spotifyAPIDataAccessObject.userExists(userId));
        if(!spotifyAPIDataAccessObject.userExists(userId)){
            loginPresenter.prepareFailView("There is no account associated with "  + userId);
        }
        else {
            String name = spotifyAPIDataAccessObject.getName(userId);
            String pfp = spotifyAPIDataAccessObject.getProfilePicture(userId);
            if (!userDataAccessObject.userExists(userId)) {
                FriendsList lst = new FriendsList();
                Inbox inbox = new Inbox();
                User user = userFactory.create(userId, lst, inbox);
                userDataAccessObject.save(user);
            }
            User user = userDataAccessObject.getUser(userId);
            LoginOutputData outputData = new LoginOutputData(
                    userId, name, pfp, user.getFriendList().get_friends(), false);
            System.out.println("userid: " + userId);
            System.out.println("name: " + name);
            System.out.println("friends: " + user.getFriendList().get_friends());
            loginPresenter.prepareSuccessView(outputData);
        }
    }
}
