package interface_adapter.home_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginPresenter;
import use_case.home_page.HomePageOutputData;
import use_case.home_page.HomePageOutPutBoundary;

public class HomePagePresenter implements HomePageOutPutBoundary{

    private final HomePageViewModel homePageViewModel;
    private ViewManagerModel viewManagerModel;

    public HomePagePresenter(HomePageViewModel homePageViewModel, ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(HomePageOutputData response) {
        HomePageState state = homePageViewModel.getState();
        state.setUserID(response.getUserID());
        state.setUserName(response.getUsername());
        state.setPfp(response.getPfp());
        state.setFriendsList(response.getFriendsList().get_friends());
        this.homePageViewModel.setState(state);
        this.homePageViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homePageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
