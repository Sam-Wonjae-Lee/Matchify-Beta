package entity;

import java.util.ArrayList;
import java.util.List;

public class FriendsList {
    private final List<String> friends = new ArrayList<>();

    public void add_friend(String user_id){
        this.friends.add(user_id);
    }

    public void remove_friend(String user_id){
        // removes invite from inbox
        this.friends.remove(user_id);
    }

    public List<String> get_friends(){
        return this.friends;
    }
}
