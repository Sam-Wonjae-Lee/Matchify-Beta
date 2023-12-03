package use_case.home_page;

import entity.User;

public interface HomePageUserDataAccessInterface {
    User getUser(String userID);
}
