package interface_adapter.home_page;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Main";
    public static final String Find_Matches_Label = "Find Matches";
    public static final String Inbox_Label = "Inbox";
    public static final String Friend_List_Label = "Friend's List";
    private HomePageState state = new HomePageState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public HomePageViewModel(String viewName) {
        super(viewName);
    }

    public void setState(HomePageState state){
        this.state = state;
    }

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
