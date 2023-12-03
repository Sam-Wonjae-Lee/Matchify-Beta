package use_case.open_inbox;

import java.util.List;

public class OpenInboxOutputData {

    private final String user_id;

    private final String username;

    private final List<String> inbox;

    public OpenInboxOutputData(String userId, String username, List<String> inbox) {
        user_id = userId;
        this.username = username;
        this.inbox = inbox;
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

}
