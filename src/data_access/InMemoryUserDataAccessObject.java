package data_access;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements OpenInboxUserDataAccessInterface, SendInviteUserDataAccessInterface, LoginUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();


    @Override
    public void add_friend(String inviteID, String userID) {
        // TODO: PLZ CHECK NAMING CONVENTION
        users.get(inviteID).getFriendList().add_friend(userID);
    }

    @Override
    public boolean userExists(String userId) {
        return false;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User getUser(String userID) {
        return users.get(userID);
    }

    @Override
    public void add_user_genre(String userID, HashMap<String, Integer> genre) {
        users.get(userID).getGenres().setGenreMap(genre);
    }

    @Override
    public void addToInbox(String userID, String invitedUserID) {
        // TODO: PLZ CHECK NAMING CONVENTION
        System.out.println("in memory dao add to inbox was called");
        users.get(userID).getInbox().add_invite(invitedUserID);
    }
}
