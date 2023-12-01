package use_case.login;

public class LoginOutputData {

    private final String userId;
    private String username;
    private String profilePictureUrl;
    private boolean useCaseFailed;

    public LoginOutputData(String userId, String username, String profilePictureUrl, boolean useCaseFailed) {
        this.userId = userId;
        this.username = username;
        this.profilePictureUrl = profilePictureUrl;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

}
