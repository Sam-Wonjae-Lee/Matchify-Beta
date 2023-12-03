package use_case.match;

import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class MatchOutPutData {
    boolean useCaseFailed;
    final List<String> userIDArrayList;
    String clientUserID;
    private SpotifyApiCallGetInfoDataAccessObject spotifyAPI;

    public MatchOutPutData(boolean useCaseFailed, List<String> userIDArrayList, String clientUserID) {
        this.useCaseFailed = useCaseFailed;
        this.userIDArrayList = userIDArrayList;
        this.clientUserID = clientUserID;
        this.spotifyAPI = new SpotifyApiCallGetInfoDataAccessObject();
    }
    public List<String> getuserIDArrayList() {
        return userIDArrayList;
    }
    public List<String> getUserNames() {
        ArrayList<String> userNamesList = new ArrayList<>();
        for (String ID : userIDArrayList)
            userNamesList.add(spotifyAPI.getName(ID));
        return userNamesList;
    }

    public String getClientUserID() {
        return clientUserID;
    }
}
