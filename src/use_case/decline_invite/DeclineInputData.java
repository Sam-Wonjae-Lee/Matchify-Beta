package use_case.decline_invite;

public class DeclineInputData {
    final private String username;
    public DeclineInputData(String username) {this.username = username; }
    String getUsername() { return username; }
}
