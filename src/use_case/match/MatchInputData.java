package use_case.match;

import entity.CommonUser;

public class MatchInputData {
    private final String userID;

    public MatchInputData(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
