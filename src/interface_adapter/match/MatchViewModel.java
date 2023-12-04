package interface_adapter.match;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class MatchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Matches";
    public static final String BACK_BUTTON_LABEL = "Back";

    public static final String MATCH_BUTTON_LABEL = "Match";

    public static final String SEND_INVITE_BUTTON_LABEL = "Add Friend";
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
