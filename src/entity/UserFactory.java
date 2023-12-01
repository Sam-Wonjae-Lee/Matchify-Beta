package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface UserFactory {
    User create(String name, List<String> friends, List<String> inbox);
}