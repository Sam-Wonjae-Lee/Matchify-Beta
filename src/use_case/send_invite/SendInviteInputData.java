package use_case.send_invite;

public class SendInviteInputData {

    private final String userID;

    private final String invitedUserID;

    public SendInviteInputData(String userID, String invitedUserID) {
        this.userID = userID;
        this.invitedUserID = invitedUserID;
    }

    String getUserID(){
        return userID;
    }

    String getInvitedUserID(){
        return invitedUserID;
    }
}
