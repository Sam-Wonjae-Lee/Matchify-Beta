package use_case.match;

import entity.CommonUser;

import java.util.ArrayList;
import java.util.List;

public class MatchOutPutData {
    boolean useCaseFailed;
    final List<String> userArrayList;

    public MatchOutPutData(boolean useCaseFailed, List<String> userArrayList) {
        this.useCaseFailed = useCaseFailed;
        this.userArrayList = userArrayList;
    }
    public List<String> getuserArrayList() {
        return userArrayList;
    }
}
