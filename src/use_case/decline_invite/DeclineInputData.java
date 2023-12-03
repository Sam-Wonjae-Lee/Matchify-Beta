package use_case.decline_invite;

public class DeclineInputData {

    final private String username;
    final private String friend_id;
    public DeclineInputData(String username, String friend_id) {
        this.username = username;
        this.friend_id = friend_id;
    }
    String getUsername() { return username; }

    String getFriend_id(){
        return this.friend_id;
    }

}
