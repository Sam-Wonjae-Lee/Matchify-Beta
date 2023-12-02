package use_case.accept_invite;

import entity.User;

public interface AcceptUserDataAccessInterface {
    void add_friend(String user_id, String friend_id);
}
