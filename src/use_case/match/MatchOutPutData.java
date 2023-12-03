package use_case.match;

import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class MatchOutPutData {
    boolean useCaseFailed;
    final List<User> userArrayList;

    public MatchOutPutData(boolean useCaseFailed, List<User> userArrayList) {
        this.useCaseFailed = useCaseFailed;
        this.userArrayList = userArrayList;
    }
    public List<User> getuserArrayList() {
        return userArrayList;
    }
}
