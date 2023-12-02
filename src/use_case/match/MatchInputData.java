package use_case.match;

import entity.CommonUser;

public class MatchInputData {
    private final String user;

    public MatchInputData(String user) {
        this.user = user;
    }

    public String getUserID() {
        return user;
    }
}
