package entity;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;

public class FriendsList {
    private ArrayList<String> user_ids;

    public FriendsList(){
        // initialize FriendsList
        this.user_ids = new ArrayList<>();
    }

    public void add_friend(String user_id){
        // adds friend into the Friendslist
        this.user_ids.add(user_id);
    }

    public void remove_friends(String user_id){
        // removes friends from FriendsList
        try{
            this.user_ids.remove(user_id);
        }
        catch{
            throw new InaccessibleObjectException();
        }
    }
}
