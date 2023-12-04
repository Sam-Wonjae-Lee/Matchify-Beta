package use_case.match;

import java.util.List;

public class MatchOutPutData {
    private final List<String> matched_list;
    private final String user_id;


    public MatchOutPutData(List<String> matchedList, String userId) {
        matched_list = matchedList;
        user_id = userId;
    }

    public List<String> getMatched_list(){
        return matched_list;
    }

    public String getUser_id(){
        return user_id;
    }
}
