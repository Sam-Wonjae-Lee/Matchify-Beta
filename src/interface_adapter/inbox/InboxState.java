package interface_adapter.inbox;

import java.util.ArrayList;
import java.util.List;

public class InboxState {

    public List<String> inbox = new ArrayList<>();
    private String username = "";

    private String user_id = null;

    private String friend_id = null;

    public InboxState(InboxState copy) {
        username = copy.username;
        inbox = copy.inbox;
        user_id = copy.user_id;
        friend_id = copy.friend_id;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InboxState() {}

    public String getUsername() {
        return username;
    }

    public String getUser_id(String username) { return user_id; }

    public String getFriend_id(String username) { return friend_id; }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getInbox(){
        return inbox;
    }

    public void setInbox(List<String> lst){
        this.inbox = lst;
    }
}
