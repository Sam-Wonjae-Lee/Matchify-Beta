package interface_adapter.match;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class MatchViewModel extends ViewModel {
    public static final String INVITE_BUTTON_LABEL_1 = "Invite";
    public static final String INVITE_BUTTON_LABEL_2 = "Invite";
    public static final String INVITE_BUTTON_LABEL_3 = "Invite";
    public static final String TITLE_LABEL = "Matches";
    public static final String BACK_BUTTON_LABEL = "Back";
    private MatchState state = new MatchState();


    public MatchViewModel() {
        super("matches");
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void setState(MatchState matchState) {
        this.state = matchState;
    }

    public MatchState getState() {
        return state;
    };

}
