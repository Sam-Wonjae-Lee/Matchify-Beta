package use_case.match;

import entity.CommonUser;

public class MatchInputData {
    private final CommonUser user;

    public MatchInputData(CommonUser user) {
        this.user = user;
    }

    public CommonUser getUser() {
        return user;
    }
}
