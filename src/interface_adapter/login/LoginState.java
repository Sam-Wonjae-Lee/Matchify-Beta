package interface_adapter.login;

public class LoginState {

    private String userId = "";
    private String userIdError = null;

    public LoginState(LoginState copy) {
        userId = copy.userId;
        userIdError = copy.userIdError;
    }

    public LoginState() {}

    public String getUserId() {
        return userId;
    }

    public String getUserIdError() {
        return userIdError;
    }

}
