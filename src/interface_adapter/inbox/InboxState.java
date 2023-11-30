package interface_adapter.inbox;

import java.util.ArrayList;

public class InboxState {

    public ArrayList<String> inbox = new ArrayList<>();
    private String username = "";

    public InboxState(InboxState copy) {
        username = copy.username;
        inbox = copy.inbox;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InboxState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getInbox(){
        return inbox;
    }

    public void setInbox(ArrayList<String> lst){
        this.inbox = lst;
    }
}
