package data_access;

import entity.User;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements OpenInboxUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User get(String username) {
        return null;
    }

}
