package interface_adapter.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MatchState {
    private List<String> MATCHED_USERSNAMES = new ArrayList<>(Arrays.asList("user1", "user2", "user3"));
    private String MATCHED_USERS_ERROR = null;
    private String CLIENT_USERID;
    private List<String> MATCHED_USERSID;

    public MatchState(MatchState copy) {
        MATCHED_USERSID = copy.MATCHED_USERSID;
        MATCHED_USERS_ERROR = copy.MATCHED_USERS_ERROR;
    }

    public MatchState() {
    }

//  Getters
    public List<String> getUSERNAMES() { return MATCHED_USERSNAMES; }

    public List<String> getMATCHED_USERSID() { return MATCHED_USERSID; }

    public String getMatchedUsersError() {
        return MATCHED_USERS_ERROR;
    }

    public String getCLIENT_USERID() {
        return CLIENT_USERID;
    }

//  Setters

    public void setCLIENT_USERID(String clientUserid) {
        CLIENT_USERID = clientUserid;
    }

    public void setMATCHED_USERSID(List<String> matchedUsersID) { MATCHED_USERSID = matchedUsersID; }

    public void setMATCHED_USERS_ERROR(String matchedUsersError) {
        MATCHED_USERS_ERROR = matchedUsersError;
    }

    public void setMATCHED_USERSNAMES(List<String> matchedUsersNames) { MATCHED_USERSNAMES = matchedUsersNames; }

}
