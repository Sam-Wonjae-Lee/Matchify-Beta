package interface_adapter.Match;

import entity.CommonUser;
import use_case.Match.MatchOutPutData;
import use_case.Match.MatchOutputBoundary;

import java.util.ArrayList;

public class MatchPresenter implements MatchOutputBoundary {
    public MatchOutPutData outPutData;
    public MatchViewModel matchViewModel;
    public MatchPresenter(MatchOutPutData outPutData, MatchViewModel matchViewModel) {
        this.outPutData = outPutData;
        this.matchViewModel = matchViewModel;
    }
//LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
//
//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    @Override
    public void prepareSuccessView(MatchOutPutData userList) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
