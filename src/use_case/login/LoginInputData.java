package use_case.login;

public class LoginInputData {

    private final String userId;

    public LoginInputData(String userId) {
        this.userId = userId;
    }

    String getUserId() {
        return userId;
    }

}
