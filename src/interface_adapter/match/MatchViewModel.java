package interface_adapter.match;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MatchViewModel extends ViewModel {
    public static final String FOLLOW_BUTTON_LABEL = "Follow";
    public static final String TITLE_LABEL = "Matches";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

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
