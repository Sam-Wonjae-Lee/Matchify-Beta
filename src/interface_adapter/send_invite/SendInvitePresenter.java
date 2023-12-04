package interface_adapter.send_invite;

import interface_adapter.match.MatchState;
import interface_adapter.match.MatchViewModel;
import use_case.send_invite.SendInviteOutputBoundary;
import use_case.send_invite.SendInviteOutputData;

import java.util.List;

public class SendInvitePresenter implements SendInviteOutputBoundary {

    private final MatchViewModel matchViewModel;

    public SendInvitePresenter(MatchViewModel matchViewModel) {
        this.matchViewModel = matchViewModel;
    }

    @Override
    public void prepareSuccessView(SendInviteOutputData user) {
        MatchState matchState = matchViewModel.getState();
        List<String>  matched_users = matchState.getMatched_users();
        matched_users.remove(user.getDelete_add_friend());
        matchState.setMatched_users(matched_users);

        this.matchViewModel.setState(matchState);
        matchViewModel.firePropertyChanged();
    }
    //to be implemented if want a fail case later
    @Override
    public void prepareFailView(String error) {

    }
}
