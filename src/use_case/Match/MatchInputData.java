package use_case.Match;

import entity.CommonUser;

public class MatchInputData {
    private final String userName;

    public MatchInputData(CommonUser user) {
        this.userName = user.getName();
    }
}
