package use_case.accept_invite;

public class AcceptOutputData {
    private final String msg;

    public AcceptOutputData(String message) { this.msg = message; }

    public String getMsg() { return msg; }
}
