package use_case.open_inbox;

import entity.User;

import java.util.HashMap;

public interface OpenInboxUserDataAccessInterface {
    User getUser(String username);

    HashMap<String,String> getUsernameMap();
}
