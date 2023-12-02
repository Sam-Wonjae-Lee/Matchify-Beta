package use_case.decline_invite;

public class DeclineInputData {
    final private String user_id;

    final private String inviter_id;
    public DeclineInputData(String userId, String inviterId) {
        user_id = userId;
        inviter_id = inviterId;}
    String getUser_id(){
        return user_id;
    }
    String getInviter_id(){
        return inviter_id;
    }
}
