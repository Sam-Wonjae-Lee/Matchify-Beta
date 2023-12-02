package use_case.accept_invite;

import entity.User;

public interface AcceptUserDataAccessInterface {
    void add_friend(Integer user_id, Integer friend_id);
}
