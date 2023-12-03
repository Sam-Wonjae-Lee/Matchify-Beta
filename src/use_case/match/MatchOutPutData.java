package use_case.match;

import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class MatchOutPutData {
    boolean useCaseFailed;
    final List<User> userArrayList;
    private SpotifyApiCallGetInfoDataAccessObject spotifyAPI;

    public MatchOutPutData(boolean useCaseFailed, List<User> userArrayList) {
        this.useCaseFailed = useCaseFailed;
        this.userArrayList = userArrayList;
        this.spotifyAPI = new SpotifyApiCallGetInfoDataAccessObject();
    }
    public List<User> getuserArrayList() {
        return userArrayList;
    }
    public List<String> getUserNames() {
        ArrayList<String> UserNameList = new ArrayList<>();
        for (User i : userArrayList) {
            String name = spotifyAPI.getName(i.getUserID());
            UserNameList.add(name);
        }
        return UserNameList;
    }
}
