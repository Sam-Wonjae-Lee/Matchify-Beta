package entity;

public interface UserFactory {
    User create(String name, FriendsList friends, Inbox inbox, Genre genres, String username);
}