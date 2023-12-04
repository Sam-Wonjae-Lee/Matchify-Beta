package use_case.send_invite;

public class SendInviteOutputData {

    // True if the invitation is sent succesfully and false if not
    private String delete_add_friend;

    public SendInviteOutputData(String delete_add_friend) {
        this.delete_add_friend = delete_add_friend;
    }

    public String getDelete_add_friend(){
        return delete_add_friend;
    }
}