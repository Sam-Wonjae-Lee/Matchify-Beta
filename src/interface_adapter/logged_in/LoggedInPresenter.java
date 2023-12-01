package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModelManager;
import use_case.HomePage.HomePageOutPutBoundary;

public class LoggedInPresenter implements HomePageOutPutBoundary{

    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        loggedInViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
