package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserFactory {
    User create(String name, FriendsList friends, Inbox inbox, Genre genres);
}