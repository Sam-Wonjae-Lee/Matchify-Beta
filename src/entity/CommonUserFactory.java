package entity;

import java.time.LocalDateTime;
import java.util.List;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, List<String> friends, List<String> inbox) {
        return new CommonUser(name, friends, inbox);
    }
}
