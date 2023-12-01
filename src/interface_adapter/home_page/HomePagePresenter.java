package interface_adapter.home_page;

import interface_adapter.ViewManagerModel;
import use_case.home_page.HomePageOutPutBoundary;

public class HomePagePresenter implements HomePageOutPutBoundary{

    private final HomePageViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public HomePagePresenter(HomePageViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
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
