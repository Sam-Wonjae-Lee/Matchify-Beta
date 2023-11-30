package use_case.open_inbox;

import entity.User;

public interface OpenInboxUserDataAccessInterface {

    public boolean existsByName(String identifier);

    User get(String username);
}
