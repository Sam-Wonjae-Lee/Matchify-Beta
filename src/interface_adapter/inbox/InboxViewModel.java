package interface_adapter.inbox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InboxViewModel extends ViewModel{
    public final String TITLE_LABEL = "Inbox View";

    private InboxState state = new InboxState();

    public static final String BACK_BUTTON_LABEL = "Back";
    private String inboxOwner;

    public InboxViewModel() {
        super("Inbox");
    }

    public void setState(InboxState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InboxState getState() {
        return state;
    }

    public String getInboxOwner() {
        return inboxOwner;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.inboxOwner = loggedInUser;
    }
}

}
