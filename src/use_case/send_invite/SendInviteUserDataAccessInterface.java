package use_case.send_invite;

import entity.User;

public interface SendInviteUserDataAccessInterface {

    void add_friend(String inviteID, String userID);

    User getUser(String userID);

    void addToInbox(String userID, String invitedUserID);
}
