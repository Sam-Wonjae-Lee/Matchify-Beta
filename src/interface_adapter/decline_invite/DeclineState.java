package interface_adapter.decline_invite;

public class DeclineState {
    private String msg = "";
    public DeclineState(DeclineState copy) { msg = copy.msg; }
    public DeclineState() {
    }
    public String getMsg() { return msg; }
    public void setMsg(String s) { this.msg = s; }
}
