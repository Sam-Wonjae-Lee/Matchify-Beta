package interface_adapter.accept_invite;

public class AcceptState {
    private String msg = "";

    public AcceptState(AcceptState copy){
        msg = copy.msg;
    }

    public AcceptState(){
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String s){
        this.msg = s;
    }
}
