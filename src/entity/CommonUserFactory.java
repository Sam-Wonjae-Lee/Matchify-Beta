package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUserFactory implements UserFactory {

    @Override
    public CommonUser create(String name, FriendsList friends, Inbox inbox, Genre genres) {
        return new CommonUser(name, friends, inbox, genres);
    }
}
