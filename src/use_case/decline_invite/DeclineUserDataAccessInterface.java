package use_case.decline_invite;

import entity.User;
public interface DeclineUserDataAccessInterface {
    void deleteInvite(String user_id, String friend_id);
}
