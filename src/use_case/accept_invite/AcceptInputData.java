package use_case.accept_invite;

public class AcceptInputData {
    final private  String user_id;
    final private  String friend_id;
    public AcceptInputData( String user_id,  String friend_id) {
        this.user_id = user_id;
        this.friend_id = friend_id;
    }
     String getUserId() { return user_id; }
     String getFriendId() { return friend_id; }
}
