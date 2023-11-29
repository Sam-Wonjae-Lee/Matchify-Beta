package interface_adapter.inbox;

public class InboxState {
    private String username = "";

    public InboxState(InboxState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InboxState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
