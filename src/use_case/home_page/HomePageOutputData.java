package use_case.home_page;

import java.util.List;

public class HomePageOutputData {
    private final String userID;
    private final String username;

    private final String pfp;

    private final List<String> friends;

    public HomePageOutputData(String userID, String username, String pfp, List<String> friendsList) {
        this.userID = userID;
        this.username = username;
        this.pfp = pfp;
        this.friends = friendsList;
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

    public List<String> getFriendsList(){
        return friends;
    }
}
