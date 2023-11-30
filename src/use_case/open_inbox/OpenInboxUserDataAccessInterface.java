package use_case.open_inbox;

import entity.User;

public interface OpenInboxUserDataAccessInterface {
    User get(String username);
}
