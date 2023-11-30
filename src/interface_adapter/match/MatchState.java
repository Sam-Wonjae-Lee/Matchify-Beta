package interface_adapter.match;

import entity.CommonUser;

import java.util.ArrayList;

public class MatchState {
    private ArrayList<CommonUser> MATCHED_USERS;

    public MatchState(MatchState copy) {
        MATCHED_USERS = copy.MATCHED_USERS;
    }
}
