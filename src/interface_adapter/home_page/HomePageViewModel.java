package interface_adapter.home_page;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class HomePageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Main";
    public static String Find_Matches_Label = "Find Matches";
    private HomePageState State = new HomePageState();




    public HomePageViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public HomePageState getState() {
        return State;
    }
}
