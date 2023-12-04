package interface_adapter.home_page;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Logged in: ";
    public static final String MATCH_BUTTON_LABEL = "Match";
    public static final String INBOX_BUTTON_LABEL = "Inbox";
    public static final String FRIENDS_LIST_LABEL = "Friends";
    public static final String LOGUOUT_BUTTON_LABEL = "Log Out";
    private HomePageState state = new HomePageState();
    public HomePageViewModel() {
        super("home page");
    }

    public void setState(HomePageState state){
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomePageState getState() {
        return state;
    }
}
