package entity;

import java.util.ArrayList;
import java.util.List;

public class Inbox {

    private final List<String> invites = new ArrayList<>();

    public void add_invite(String user_id){
        this.invites.add(user_id);
    }

    public void remove_invite(String user_id){
        // removes invite from inbox
        this.invites.remove(user_id);
    }

    public List<String> get_invites(){
        return this.invites;
    }
}
