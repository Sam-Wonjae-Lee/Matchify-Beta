package interface_adapter.inbox;

import  interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InboxViewModel extends ViewModel {
    public final String TITLE_LABEL = "Inbox View";
    public static final String ACCEPT_BUTTON_LABEL = "Accept";
    public static final String DECLINE_BUTTON_LABEL = "Decline";
    public static final String BACK_BUTTON_LABEL = "Back";
    private InboxState state = new InboxState();

    public InboxViewModel() {
        super("inbox");
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
}