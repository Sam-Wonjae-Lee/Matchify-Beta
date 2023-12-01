package interface_adapter.match;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import use_case.match.MatchOutPutData;
import use_case.match.MatchOutputBoundary;

public class MatchPresenter implements MatchOutputBoundary {
    private final MatchViewModel matchViewModel;
    private ViewManagerModel viewManagerModel;
    public MatchPresenter(MatchViewModel matchViewModel, ViewManagerModel viewManagerModel) {
        this.matchViewModel = matchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MatchOutPutData userList) {
        MatchState matchState = matchViewModel.getState();
        matchState.setMATCHED_USERS(userList.getuserArrayList());
        this.matchViewModel.setState(matchState);
        matchViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(matchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MatchState matchState = matchViewModel.getState();
        matchState.setMATCHED_USERS_ERROR(error);
        matchViewModel.firePropertyChanged();
    }
}
