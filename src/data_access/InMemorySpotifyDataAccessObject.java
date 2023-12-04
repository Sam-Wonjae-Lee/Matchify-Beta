package data_access;

import entity.CommonUser;
import entity.FriendsList;
import entity.Genre;
import entity.Inbox;
import use_case.login.LoginSpotifyAPIDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySpotifyDataAccessObject implements LoginSpotifyAPIDataAccessInterface {

    private Map<String, String> userNames = new HashMap<>();
    private Map<String, String> profilePictures = new HashMap<>();
    private Map<String, Boolean> userExists = new HashMap<>();

    private Map<String, List<String>> playlists = new HashMap<>();

    @Override
    public String getName(String userID) {
        return userNames.get(userID);
    }

    @Override
    public String getProfilePicture(String userID) {
        return profilePictures.get(userID);
    }

    @Override
    public boolean userExists(String user_id) {
        return userExists.getOrDefault(user_id, false);
    }

    @Override
    public List<String> getGenres(String artistId) {
        return null;
    }

    @Override
    public List<String> getPlaylistIds(String s) {
        return this.playlists.get(s);
    }

    public void add_playlist(String user_id, String s){
        if(this.playlists.containsKey(user_id)){
            List<String> arr = this.playlists.get(user_id);
            arr.add(s);
            this.playlists.put(user_id,arr);
        }
        else{
            List<String> arr = new ArrayList<>();
            arr.add(s);
            this.playlists.put(user_id,arr);
        }
    }

    @Override
    public List<String> getArtistsIds(String playlistId) {
        return new ArrayList<>();
    }
}
