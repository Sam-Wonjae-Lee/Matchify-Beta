package use_case.login;


import entity.*;

import java.util.HashMap;
import java.util.List;

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

    public HashMap<String, Integer> map_playlist(User user){
        List<String> playlistIds = this.spotifyAPIDataAccessObject.getPlaylistIds(user.getUserID());
        HashMap<String, Integer> allGenresFrequencyMap = new HashMap<>();
        for (String playlistId : playlistIds) {
            List<String> artistIds = this.spotifyAPIDataAccessObject.getArtistsIds(playlistId);

            for (String artistId : artistIds) {
//                System.out.println(artistId);
                List<String> genres = this.spotifyAPIDataAccessObject.getGenres(artistId);
//                System.out.println(genres);
                for (String genre : genres) {
                    allGenresFrequencyMap.put(genre, allGenresFrequencyMap.getOrDefault(genre, 0) + 1);
                }
            }
        }
//        System.out.println(allGenresFrequencyMap);
        return allGenresFrequencyMap;
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
// TODO: ASK FRANK ABOUT HashMap<String, Map<String, Integer>>
                // Save genre
                Genre genre = new Genre();
                HashMap<String, Integer> userGenre = map_playlist(userDataAccessObject.getUser(loginInputData.getUserID()));
                genre.setGenreMap(userGenre);
                userDataAccessObject.add_user_genre(loginInputData.getUserID(), genre.getGenreMap());

                FriendsList lst = new FriendsList();
                Inbox inbox = new Inbox();
                User user = userFactory.create(userId, lst, inbox, genre);
                userDataAccessObject.save(user);
            }
            User user = userDataAccessObject.getUser(userId);
            LoginOutputData outputData = new LoginOutputData(
                    userId, name, pfp, user.getFriendList().get_friends(), false);
            System.out.println("in login interactor");
            System.out.println("===");
            System.out.println("userid: " + userId);
            System.out.println("name: " + name);
            System.out.println("friends: " + user.getFriendList().get_friends());
            System.out.println("===");
            loginPresenter.prepareSuccessView(outputData);
        }
    }
}
