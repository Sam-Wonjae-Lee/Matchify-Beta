package use_case.home_page;


import entity.User;

public class HomePageInteractor implements HomePageInputBoundary{

    final HomePageOutPutBoundary presenter;

    final HomePageSpotifyAPIDataAccessInterface spotifyAPIDataAccessObject;

    final HomePageUserDataAccessInterface userDataAccessObject;

    public HomePageInteractor(HomePageOutPutBoundary homePageOutPutBoundary,
                              HomePageSpotifyAPIDataAccessInterface spotifyAPIDataAccessObject,
                              HomePageUserDataAccessInterface userDataAccessInterface) {
        this.presenter = homePageOutPutBoundary;
        this.spotifyAPIDataAccessObject = spotifyAPIDataAccessObject;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(HomePageInputData inputData) {
        String user_id = inputData.getUser_id();
        String name = spotifyAPIDataAccessObject.getName(user_id);
        String pfp = spotifyAPIDataAccessObject.getProfilePicture(user_id);
        System.out.println(userDataAccessObject.getUser(user_id));
        User user = userDataAccessObject.getUser(user_id);
        HomePageOutputData outputData = new HomePageOutputData(user_id, name, pfp, user.getFriendList().get_friends());
        presenter.prepareSuccessView(outputData);
    }
}
