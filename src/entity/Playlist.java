package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import org.javatuples.*;

public class Playlist {
    private String url;

    private CommonUser owner;

    public HashMap<String, Integer> categorize_Playlist() {
        //TODO: check this works
        user_playlist = csv.grab_playlist();
        HashMap<String, Integer> ans = new HashMap<String, Integer>();
        for(Track track : user_playlist){
            for(String attribute: track.genres){
                if(ans.containsKey(attribute)){
                    ans.put(attribute,ans.get(attribute)+1);
                }
                else{
                    ans.put(attribute,1);
                }
            }
        }
        return ans;
    }

    public int compare_playlist(Playlist other_playlist){
        //TODO: check this works
        HashMap<String, Integer> other_category = other_playlist.categorize_Playlist();
        HashMap<String, Integer> current_category = this.categorize_Playlist();
        int ans = 0;
        for(String key : current_category.keySet()){
            if(other_category.containsValue(key)){
                int val1 = other_category.get(key);
                int val2 = current_category.get(key);
                ans += Math.max(val1,val2) - Math.min(val1, val2);
            }
            else{
                ans -= current_category.get(key);
            }
        }
        for(String key : other_category.keySet()){
            if(!current_category.containsValue(key)){
                ans -= other_category.get(key);
            }
        }
        return ans;

    }

    public ArrayList<CommonUser> matchOtherPlaylist() {
        // TODO: check that this works
        all_users = csv.grab_all_user();
        HashMap<Integer, ArrayList<CommonUser>> dict = new HashMap<Integer, ArrayList<CommonUser>>();
        current_playlist_interests = this.owner.getPlaylist().categorize_Playlist();
        Pair<Integer,String>[] arr = new Pair<Integer,String>[];
        for(CommonUser user : allusers){
            int score = this.compare_playlist(user.getPlaylist());


        }
    }
}
