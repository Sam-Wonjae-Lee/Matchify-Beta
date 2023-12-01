package interface_adapter.inbox;

import java.util.ArrayList;
import java.util.List;

public class InboxState {

    public List<String> inbox = new ArrayList<>();
    private String username = "";

    private String del = null;

    public InboxState(InboxState copy) {
        username = copy.username;
        inbox = copy.inbox;
        del = copy.del;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InboxState() {}

    public String getDel() { return del; }

    public void setDel(String msg) {
        this.del = msg;
    }
    public String getUsername() {
        return username;
    }
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
