package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Playlist {
    private String url;

    private CommonUser owner;

    public HashMap<String, Integer> categorize_Playlist() {
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

    public ArrayList<CommonUser> matchOtherPlaylist() {
        // TODO: check that this works
        all_users = csv.grab();
        for(CommonUser user : allusers){
            user_playlist_dict = user;

        }
    }
}
