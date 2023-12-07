package data_access;

import entity.User;
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.home_page.HomePageUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.match.MatchUserAccessInterface;

import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteUserDataAccessInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements AcceptUserDataAccessInterface ,DeclineUserDataAccessInterface, OpenInboxUserDataAccessInterface, SendInviteUserDataAccessInterface, MatchUserAccessInterface, LoginUserDataAccessInterface, HomePageUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();


    @Override
    public void add_friend(String inviteID, String userID) {
        // TODO: PLZ CHECK NAMING CONVENTION
        users.get(inviteID).getFriendList().add_friend(userID);
    }

    @Override
    public Collection<User> get_all_users() {
        return users.values();
    }

    public boolean userExists (String userId){
        return false;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User getUser (String userID){
        return users.get(userID);
    }

    @Override
    public HashMap<String, String> getUsernameMap() {
        HashMap<String, String> ans = new HashMap<>();
        for (String key: users.keySet()){
            ans.put(key, users.get(key).getUsername());
        }
        return ans;
    }

    @Override
    public void add_user_genre(String userID, HashMap<String, Integer> genre) {

    }

    @Override
    public void addToInbox (String userID, String invitedUserID){
        // TODO: PLZ CHECK NAMING CONVENTION
        System.out.println("in memory dao add to inbox was called");
        users.get(userID).getInbox().add_invite(invitedUserID);
    }

    @Override
    public void deleteInvite(String username, String inviter) {
        this.users.remove(username,inviter);
    }
}

