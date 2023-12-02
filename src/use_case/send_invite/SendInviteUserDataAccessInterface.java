package use_case.send_invite;

import entity.User;

public interface SendInviteUserDataAccessInterface {

    void addToInbox(String inviteID, String userID);

    User getUser(String userID);
}
