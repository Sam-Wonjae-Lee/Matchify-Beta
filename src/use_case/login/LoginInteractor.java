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

    public HashMap<String, Integer> map_playlist(String user_id){
        System.out.println(user_id);
        List<String> playlistIds = this.spotifyAPIDataAccessObject.getPlaylistIds(user_id);
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
        Boolean userExists = spotifyAPIDataAccessObject.userExists(userId);
        System.out.println(userExists);
        if(!userExists){
            loginPresenter.prepareFailView("There is no account associated with "  + userId);
        }
        else {
            String name = spotifyAPIDataAccessObject.getName(userId);
            String pfp = spotifyAPIDataAccessObject.getProfilePicture(userId);
            if (!userDataAccessObject.userExists(userId)) {
                // Save genre
                Genre genre = new Genre();

                HashMap<String, Integer> userGenre = map_playlist(userId);
                genre.setGenreMap(userGenre);
                userDataAccessObject.add_user_genre(loginInputData.getUserID(), genre.getGenreMap());

                FriendsList lst = new FriendsList();
                Inbox inbox = new Inbox();
                User user = userFactory.create(userId, lst, inbox, genre, name);
                userDataAccessObject.save(user);
            }
            User user = userDataAccessObject.getUser(userId);
            HashMap<String, String> idMap = userDataAccessObject.getUsernameMap();
            LoginOutputData outputData = new LoginOutputData(
                    userId, name, pfp, user.getFriendList().get_friends(), idMap);
            loginPresenter.prepareSuccessView(outputData);
        }
    }
}
