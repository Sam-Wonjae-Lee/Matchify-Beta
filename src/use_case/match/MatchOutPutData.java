package use_case.match;

import java.util.HashMap;
import java.util.List;

public class MatchOutPutData {
    private final List<String> matched_list;
    private final String user_id;

    private final HashMap<String, String> idMap;

    public MatchOutPutData(List<String> matchedList, String userId, HashMap<String, String> idMap) {
        matched_list = matchedList;
        user_id = userId;
        this.idMap = idMap;
    }

    public List<String> getMatched_list(){
        return matched_list;
    }

    public String getUser_id(){
        return user_id;
    }

    public HashMap<String, String> getIdMap(){
        return idMap;
    }
}
