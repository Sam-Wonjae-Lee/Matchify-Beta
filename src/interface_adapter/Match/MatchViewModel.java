package interface_adapter.Match;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MatchViewModel extends ViewModel {
    public static final String FOLLOW_BUTTON_LABEL = "Follow";
    public final String TITLE = "Matches";

    public MatchViewModel(String viewName) {
        super(viewName);
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}
