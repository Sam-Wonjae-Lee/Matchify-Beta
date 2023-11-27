package use_case.open_inbox;

import java.util.List;

public class OpenInboxOutputData {

    private final String username;

    private final List<String> inbox;

    public OpenInboxOutputData(String username, List<String> inbox) {
        this.username = username;
        this.inbox = inbox;
    }

    public String getUsername(){
        return username;
    }

    public List<String> getInbox(){
        return inbox;
    }

}
