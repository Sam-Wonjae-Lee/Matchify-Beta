package use_case.login;

public class LoginInputData {

    final private String userID;

    public LoginInputData(String userID) {
        this.userID = userID;
    }

    String getUserID() {
        return userID;
    }


}
