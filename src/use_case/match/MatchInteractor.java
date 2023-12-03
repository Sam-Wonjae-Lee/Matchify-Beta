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
        System.out.println(hashSet);
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
    public Map<String, Integer> map_playlist(User user){
        List<String> playlistIds = this.matchSpotifyAccessInterface.getPlaylistIds(user.getUserID());
        Map<String, Integer> allGenresFrequencyMap = new HashMap<>();
        for (String playlistId : playlistIds) {
            List<String> artistIds = this.matchSpotifyAccessInterface.getArtistsIds(playlistId);

            for (String artistId : artistIds) {
                List<String> genres = this.matchSpotifyAccessInterface.getGenres(artistId);

                for (String genre : genres) {
                    allGenresFrequencyMap.put(genre, allGenresFrequencyMap.getOrDefault(genre, 0) + 1);
                }
            }
        }
        return allGenresFrequencyMap;
    }

    @Override
    public void execute(MatchInputData matchInputData) {
        User client_user = matchUserAccessInterface.getUser(matchInputData.getUserID());
        Map<String, Integer> client_map = this.map_playlist(client_user);
        HashMap<Integer, User> ans = new HashMap<>();
        for(User user: this.matchUserAccessInterface.get_all_users()){
            Map<String, Integer> user_map = this.map_playlist(user);
            int score = this.compare_other_playlist(client_map, user_map);
            ans.put(score,user);
        }

        List<Integer> sorted_keys = new ArrayList<>(ans.keySet());
        Collections.sort(sorted_keys);

        List<User> matchedUsers = new ArrayList<>();

//      This for loop adds users to List from Hashmap
        for (int keys : sorted_keys) {
//          does not add user to list if the user is the Client
            if (!client_user.equals(ans.get(keys))) {
//              adds to list until length is 3
                if (matchedUsers.size() != 3) {
                    matchedUsers.add(ans.get(keys));
                }
                else {
                    break;
                }
            }
        }

        if (matchedUsers.isEmpty()) {
            matchPresenter.prepareFailView("Unable to find Matches, please try again later.");
        }
        else {
            MatchOutPutData matchOutPutData = new MatchOutPutData(true, matchedUsers, client_user);
            matchPresenter.prepareSuccessView(matchOutPutData);
        }

    }
}
