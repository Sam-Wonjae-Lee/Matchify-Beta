package interface_adapter.match;

import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class MatchState {
    private List<User> MATCHED_USERS;
    private List<String> MATCHED_USERSNAMES;
    private String MATCHED_USERS_ERROR = null;
    private String CLIENT_USERID;

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

    public List<String> getUSERNAMES() { return MATCHED_USERSNAMES; }

    public String getMatchedUsersError() {
        return MATCHED_USERS_ERROR;
    }

    public String getCLIENT_USERID() {
        return CLIENT_USERID;
    }

//  Setters
    public void setMATCHED_USERS(List<User> matchedUsers) {
        MATCHED_USERS = matchedUsers;
    }

    public void setCLIENT_USERID(String clientUserid) {
        CLIENT_USERID = clientUserid;
    }

    public void setMATCHED_USERS_ERROR(String matchedUsersError) {
        MATCHED_USERS_ERROR = matchedUsersError;
    }

    public void setMATCHED_USERSNAMES(List<String> matchedUsersNames) { MATCHED_USERSNAMES = matchedUsersNames; }

}
