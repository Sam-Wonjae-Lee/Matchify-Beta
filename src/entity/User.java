package entity;

import java.util.ArrayList;
import java.util.List;

public interface User {

    String getUserID();

    List<String> getFriends();

    List<String> getInbox();
}
