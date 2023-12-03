package use_case.open_inbox;

import entity.User;

public interface OpenInboxUserDataAccessInterface {
    User getUser(String username);
}
