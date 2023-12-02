package interface_adapter.home_page;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Main";
    public static final String Find_Matches_Label = "Find Matches";
    public static final String Inbox_Label = "Inbox";
    public static final String Friend_List_Label = "Friend's List";
    private HomePageState State = new HomePageState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);





    public HomePageViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.State);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomePageState getState() {
        return State;
    }
}
