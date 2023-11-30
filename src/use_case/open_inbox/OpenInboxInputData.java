package use_case.open_inbox;

public class OpenInboxInputData {

    final private String username;
    public OpenInboxInputData(String username){
        this.username = username;
    }

    String getUsername(){
        return username;
    }
}
