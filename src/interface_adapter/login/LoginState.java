package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String usernameError = null;

    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }
}
