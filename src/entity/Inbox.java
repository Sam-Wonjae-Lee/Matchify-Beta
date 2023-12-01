package entity;

import java.util.HashSet;

public class Inbox {

    private HashSet<String> user_ids;

    public Inbox(){
        this.user_ids = new HashSet<String>();
    }

    public void add_invite(String user_id){
        this.user_ids.add(user_id);
    }

    public void remove_invite(String user_id){
        // removes invite from inbox
        this.user_ids.remove(user_id);
    }

    public HashSet<String> get_invites(){
        return this.user_ids;
    }
}
