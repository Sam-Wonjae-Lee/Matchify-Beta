package interface_adapter.home_page;

import java.util.List;

public class HomePageState {
    private String userID;

    private String username;
    private String pfp;

    private List<String> friendlist;

    public HomePageState(HomePageState copy) {
        this.userID = copy.userID;
        this.username = copy.username;
        this.pfp = copy.pfp;
        this.friendlist = copy.friendlist;
    }

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

    public void setFriendlist(List<String> lst){
        this.friendlist = lst;
    }
}
