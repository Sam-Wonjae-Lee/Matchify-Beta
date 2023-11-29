package use_case.match;

import entity.CommonUser;

import java.util.ArrayList;

public class MatchOutPutData {
    boolean useCaseFailed;
    final ArrayList<CommonUser> userArrayList;

    public MatchOutPutData(boolean useCaseFailed, ArrayList<CommonUser> userArrayList) {
        this.useCaseFailed = useCaseFailed;
        this.userArrayList = userArrayList;
    }
    public ArrayList<CommonUser> getuserArrayList() {
        return userArrayList;
    }
}
