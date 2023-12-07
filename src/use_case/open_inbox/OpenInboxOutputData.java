package use_case.open_inbox;

import java.util.HashMap;
import java.util.List;

public class OpenInboxOutputData {

    private final String user_id;

    private final String username;

    private final List<String> inbox;

    private final HashMap<String, String> user_to_name;

    public OpenInboxOutputData(String userId, String username, List<String> inbox, HashMap<String, String> userToName) {
        user_id = userId;
        this.username = username;
        this.inbox = inbox;
        user_to_name = userToName;
    }

    public String getUser_id(){
        return user_id;
    }
    public String getUsername(){
        return username;
    }

    public List<String> getInbox(){
        return inbox;
    }

    public HashMap<String,String> getUser_to_name(){
        return user_to_name;
    }

}
