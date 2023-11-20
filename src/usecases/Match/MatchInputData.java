package usecases.Match;

import entity.User;

public class MatchInputData {
    private final String userName;

    public MatchInputData(User user) {
        this.userName = user.getName();
    }
}
