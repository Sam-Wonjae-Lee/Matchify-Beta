package entity;

public class CommonUserFactory implements UserFactory {

    @Override
    public CommonUser create(String name, FriendsList friends, Inbox inbox, Genre genres, String username) {
        return new CommonUser(name, friends, inbox, genres, username);
    }
}
