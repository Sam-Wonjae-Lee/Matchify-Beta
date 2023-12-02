package data_access;

import entity.User;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements OpenInboxUserDataAccessInterface, SendInviteDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public void addToInbox(String inviteID, String userID) {

    }

    @Override
    public User getUser(String userID) {
        return users.get(userID);
    }
}
