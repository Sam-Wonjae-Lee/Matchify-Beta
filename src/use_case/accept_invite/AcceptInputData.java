package use_case.accept_invite;

public class AcceptInputData {
    final private String username;
    public AcceptInputData(String username) {this.username = username; }
    String getUsername() { return username; }
}
