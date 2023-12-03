package use_case.open_inbox;

public class OpenInboxInputData {

    final private String user_id;

    final private String username;
    public OpenInboxInputData(String user_id, String username){
        this.user_id = user_id;
        this.username = username;
    }

    String getUser_id(){
        return user_id;
    }

    String getUsername(){
        return username;
    }
}
