package interface_adapter.match;

import use_case.match.MatchOutPutData;
import use_case.match.MatchOutputBoundary;

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
