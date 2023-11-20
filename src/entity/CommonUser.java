package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User{

    private String photo;

    private String name;

    private int age;

    private String bio;

    // Includes Friend and CloseFriend class
    // Test if there are errors with adding CloseFriend to friends array
    private ArrayList<Friend> friends;

//    private List<CloseFriend> closeFriends;

    private Playlist spotifyPlaylist;

    private Invite invites;

    private ArrayList<String> inbox;

    public void changePhoto() {}

    public void changeBio() {}

    public void changePlaylist() {}

    // Two constructors needed for creating the User and for user id.
    public CommonUser(String photo, String name, int age, Playlist spotifyPlaylist) {
        this.photo = photo;
        this.age = age;
//        this.bio = bio;
        this.name = name;
        this.spotifyPlaylist = spotifyPlaylist;
        this.friends = new ArrayList<>();
        this.inbox = new ArrayList<>();
//        this.closeFriends = new ArrayList<>();
    }

//    public void ChangeBio(String new_bio) {
//        this.bio = new_bio;
//    }

//    public void ChangePlaylist(Playlist new_playlist) {
//        this.spotifyPlaylist = new_playlist;
//    }

    public ArrayList<String> getInbox(){
        return inbox;
    }
//
//    @Override
//    public void addFriend(Friend friend) {
//        this.friends.add(friend);
//    }

    @Override
    public void setPlaylist(Playlist playlist) {
        this.spotifyPlaylist = playlist;
    }

    @Override
    public void addFriendRequest(String request) {
        this.inbox.add(request);
    }

    @Override
    public void addFriend(int userID, String name) {
        Friend friend = new Friend(userID, name);
        friends.add(friend);
    }

    public String getName() {return this.name;}

    @Override
    public String getPicture() {
        return this.photo;
    }

    @Override
    public void setPicture(String photo) {
        this.photo = photo;
    }

    @Override
    public Playlist getPlaylist() {
        return spotifyPlaylist;
    }

    @Override
    public ArrayList<Friend> getFriendList() {
        return friends;
    }
//
//    public User() {}

}
