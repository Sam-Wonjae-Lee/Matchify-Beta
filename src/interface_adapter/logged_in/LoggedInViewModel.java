package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class LoggedInViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Main";
    public static String Find_Matches_Label = "Find Matches";
    private LoggedInState State = new LoggedInState();


    public LoggedInViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public LoggedInState getState() {
        return State;
    }
}
