package interface_adapter.match;

import entity.User;
import interface_adapter.ViewManagerModel;
import use_case.match.MatchOutPutData;
import use_case.match.MatchOutputBoundary;

import java.util.ArrayList;

public class MatchPresenter implements MatchOutputBoundary {
    private final MatchViewModel matchViewModel;
    private ViewManagerModel viewManagerModel;
    public MatchPresenter(MatchViewModel matchViewModel, ViewManagerModel viewManagerModel) {
        this.matchViewModel = matchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MatchOutPutData response) {
        MatchState matchState = matchViewModel.getState();

        matchState.setUser_id(response.getUser_id());
        matchState.setMatched_users(response.getMatched_list());

        this.matchViewModel.setState(matchState);
        matchViewModel.firePropertyChanged();


        this.viewManagerModel.setActiveView(matchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        MatchState matchState = matchViewModel.getState();
        matchState.setMatchError(error);
        matchViewModel.firePropertyChanged();
    }
}
