package use_case.open_inbox;

import java.util.ArrayList;

public class OpenInboxOutputData {

    private final String username;

    private final ArrayList<String> inbox;

    public OpenInboxOutputData(String username, ArrayList<String> inbox) {
        this.username = username;
        this.inbox = inbox;
    }

    public String getUsername(){
        return username;
    }

    public ArrayList<String> getInbox(){
        return inbox;
    }

}
