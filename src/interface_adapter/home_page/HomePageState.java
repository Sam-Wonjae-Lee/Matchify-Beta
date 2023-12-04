package interface_adapter.home_page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePageState {
    private String userID;

    private String username;
    private String pfp;

    private HashMap<String, String> idMap;

    private List<String> friendlist = new ArrayList<>();

    public HomePageState(HomePageState copy) {
        this.userID = copy.userID;
        this.username = copy.username;
        this.pfp = copy.pfp;
        this.friendlist = copy.friendlist;
        this.idMap = copy.idMap;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public HomePageState() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPfp(){
        return pfp;
    }

    public void setPfp(String pfp){
        this.pfp = pfp;
    }

    public List<String> getFriendlist(){
        return friendlist;
    }

    public void setFriendsList(List<String> lst){
        this.friendlist = lst;
    }

    public void setIdMap(HashMap<String, String> idMap){
        this.idMap = idMap;
    }

    public HashMap<String, String> getIdMap(){
        return idMap;
    }
}
