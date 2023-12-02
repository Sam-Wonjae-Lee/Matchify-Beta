package use_case.send_invite;

public class SendInviteOutputData {

    // True if the invitation is sent succesfully and false if not
    private boolean sent;

    public SendInviteOutputData(boolean sent){
        this.sent = sent;
    }
}
