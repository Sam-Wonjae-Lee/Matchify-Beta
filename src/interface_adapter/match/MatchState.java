package interface_adapter.match;

import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class MatchState {
    private List<User> MATCHED_USERS;
    private String MATCHED_USERS_ERROR = null;

    public MatchState(MatchState copy) {
        MATCHED_USERS = copy.MATCHED_USERS;
        MATCHED_USERS_ERROR = copy.MATCHED_USERS_ERROR;
    }

    public MatchState() {
    }

//  Getters
    public List<User> getMatchedUsers() {
        return MATCHED_USERS;
    }

    public String getMatchedUsersError() {
        return MATCHED_USERS_ERROR;
    }

//  Setters
    public void setMATCHED_USERS(List<User> matchedUsers) {
        MATCHED_USERS = matchedUsers;
    }

    public void setMATCHED_USERS_ERROR(String matchedUsersError) {
        MATCHED_USERS_ERROR = matchedUsersError;
    }

}
