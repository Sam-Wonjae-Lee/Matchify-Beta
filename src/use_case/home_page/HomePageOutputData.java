package use_case.home_page;

import entity.FriendsList;

public class HomePageOutputData {
    private final String userID;
    private final String username;

    private final String pfp;

    private final FriendsList friendsList;

    public HomePageOutputData(String userID, String username, String pfp, FriendsList friendsList) {
        this.userID = userID;
        this.username = username;
        this.pfp = pfp;
        this.friendsList = friendsList;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID(){
        return userID;
    }

    public String getPfp(){
        return pfp;
    }

    public FriendsList getFriendsList(){
        return friendsList;
    }
}
