package interface_adapter.match;

import entity.CommonUser;

import java.util.ArrayList;

public class MatchState {
    private ArrayList<CommonUser> MATCHED_USERS;
    private String MATCHED_USERS_ERROR = null;

    public MatchState(MatchState copy) {
        MATCHED_USERS = copy.MATCHED_USERS;
        MATCHED_USERS_ERROR = copy.MATCHED_USERS_ERROR;
    }

//  Getters
    public ArrayList<CommonUser> getMatchedUsers() {
        return MATCHED_USERS;
    }

    public String getMatchedUsersError() {
        return MATCHED_USERS_ERROR;
    }

//  Setters
    public void setMATCHED_USERS(ArrayList<CommonUser> matchedUsers) {
        MATCHED_USERS = matchedUsers;
    }

    public void setMATCHED_USERS_ERROR(String matchedUsersError) {
        MATCHED_USERS_ERROR = matchedUsersError;
    }

}
