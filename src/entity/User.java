package entity;

import java.util.List;

public class User {

    private String photo;

    private String name;

    private int age;

    private String bio;

    // Includes Friend and CloseFriend class
    // Test if there are errors with adding CloseFriend to friends array
    private List<Friend> friends;

    private List<CloseFriend> closeFriends;

    private Playlist spotifyPlaylist;

    private Invite invites;

    public void changePhoto() {}

    public void changeBio() {}

    public void changePlaylist() {}

    // Two constructors needed for creating the User and for user id.
//    public User() {}
//
//    public User() {}

}
