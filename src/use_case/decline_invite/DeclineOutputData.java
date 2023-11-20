package use_case.decline_invite;

public class DeclineOutputData {
    private final String msg;

    public DeclineOutputData(String message) { this.msg = message; }
    public String getMsg() { return msg; }
}
