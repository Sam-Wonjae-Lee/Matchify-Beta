package use_case.send_invite;

import entity.User;

public interface SendInviteDataAccessInterface {

    void addToInbox(String inviteID, String userID);

    User getUser(String userID);
}
