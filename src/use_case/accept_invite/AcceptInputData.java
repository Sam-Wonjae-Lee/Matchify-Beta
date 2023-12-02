package use_case.accept_invite;

public class AcceptInputData {
    final private Integer user_id;
    final private Integer friend_id;
    public AcceptInputData(Integer user_id, Integer friend_id) {
        this.user_id = user_id;
        this.friend_id = friend_id;
    }
    Integer getUserId() { return user_id; }
    Integer getFriendId() { return friend_id; }
}
