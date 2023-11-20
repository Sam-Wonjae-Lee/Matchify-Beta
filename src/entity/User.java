package entity;

import java.util.ArrayList;

public interface User {

    String getName();
    String getPicture();
    void setPicture(String photo);
    Playlist getPlaylist();
    ArrayList<Friend> getFriendList();
    ArrayList<String> getInbox();
    void addFriend(int userID, String name);
    void setPlaylist(Playlist playlist);
    void addFriendRequest(String request);

}
