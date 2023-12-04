package use_case.match;
import entity.User;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class MatchInteractor implements MatchInputboundary{
    public MatchOutputBoundary matchPresenter;
    public MatchUserAccessInterface matchUserAccessInterface;

    public MatchSpotifyAccessInterface matchSpotifyAccessInterface;

    public MatchInteractor(MatchOutputBoundary matchOutputBoundary, MatchUserAccessInterface matchUserAccessInterface, MatchSpotifyAccessInterface matchSpotifyAccessInterface) {
        this.matchPresenter = matchOutputBoundary;
        this.matchUserAccessInterface = matchUserAccessInterface;
        this.matchSpotifyAccessInterface = matchSpotifyAccessInterface;
    }

    public int compare_other_playlist(Map<String, Integer> map1, Map<String, Integer> map2){
        Integer ans = 0;
        HashSet<String> hashSet = new HashSet<>();
        for(String key: map1.keySet()){
            hashSet.add(key);
        }
        for(String key: map2.keySet()){
            hashSet.add(key);
        }
        for(String key: hashSet){
            if(map1.containsKey(key) && map2.containsKey(key)){
                ans += Math.max(map1.get(key),map2.get(key)) - Math.min(map1.get(key),map2.get(key));
            }
            else if (map1.containsKey(key)){
                ans += map1.get(key);
            }
            else{
                ans += map2.get(key);
            }
        }
        return ans;
    }
    public HashMap<String, Integer> map_playlist(User user){
        List<String> playlistIds = this.matchSpotifyAccessInterface.getPlaylistIds(user.getUserID());
        HashMap<String, Integer> allGenresFrequencyMap = new HashMap<>();
        System.out.println(playlistIds);
        for (String playlistId : playlistIds) {
            List<String> artistIds = this.matchSpotifyAccessInterface.getArtistsIds(playlistId);

            for (String artistId : artistIds) {
//                System.out.println(artistId);
                List<String> genres = this.matchSpotifyAccessInterface.getGenres(artistId);
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
    public void execute(MatchInputData matchInputData) {
        User client_user = matchUserAccessInterface.getUser(matchInputData.getUserID());
        Map<String, Integer> client_map = client_user.getGenres().getGenreMap();
        HashMap<Integer, User> ans = new HashMap<>();
      
        for (User user : this.matchUserAccessInterface.get_all_users()) {
            Map<String, Integer> user_map = user.getGenres().getGenreMap();
            int score = this.compare_other_playlist(client_map, user_map);
            ans.put(score, user);
        }
        List<Integer> sorted_keys = new ArrayList<>(ans.keySet());
        Collections.sort(sorted_keys);

        List<String> matchedUsers = new ArrayList<>();

        String client_user_id = client_user.getUserID();
//      This for loop adds users to List from Hashmap
        for (int keys : sorted_keys) {
            if (matchedUsers.size() == 3) {
                break;
            }
//          does not add user to list if the user is the Client or if the client is already friends with the user
            String user_id = ans.get(keys).getUserID();
            if (!client_user_id.equals(user_id) &&
                    !client_user.getFriendList().get_friends().contains(user_id)){
                matchedUsers.add(ans.get(keys).getUserID());
            }
        }
        if (matchedUsers.isEmpty()) {
            matchPresenter.prepareFailView("Unable to find Matches, please try again later.");
        }
        else {

            MatchOutPutData matchOutPutData = new MatchOutPutData(
                    matchedUsers, client_user_id, matchUserAccessInterface.getUsernameMap());

            MatchOutPutData matchOutPutData = new MatchOutPutData(matchedUsers, client_user_id);

            matchPresenter.prepareSuccessView(matchOutPutData);
        }

    }
}
