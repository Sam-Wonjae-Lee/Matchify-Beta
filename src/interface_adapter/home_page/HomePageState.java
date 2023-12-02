package interface_adapter.home_page;

public class HomePageState {
    private String userID;

    public HomePageState(HomePageState copy) {
        this.userID = copy.userID;
    }

    public HomePageState() {

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
