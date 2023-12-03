package use_case.home_page;

public interface HomePageSpotifyAPIDataAccessInterface {
    String getName(String userID);

    String getProfilePicture(String userID);
}
