package use_case.match;
import entity.CommonUserFactory;
import entity.Playlist;
import entity.CommonUser;
import entity.UserFactory;

import java.util.ArrayList;

public class MatchInteractor implements MatchInputobundary{
//    private Playlist playlist;
    MatchOutputBoundary matchPresenter;
    MatchDataAccessInterface matchDataAccessInterface;
    public MatchInteractor(MatchOutputBoundary matchOutputBoundary, MatchDataAccessInterface matchDataAccessInterface) {
        this.matchPresenter = matchOutputBoundary;
        this.matchDataAccessInterface = matchDataAccessInterface;
    }

    @Override
    public void execute(MatchInputData matchInputData) {
//        ArrayList<CommonUser> matchedUsers =
//        TODO: Find a way to turn playlistID into Array playlist.
//
//                matchDataAccessInterface.getUserPlaylistID(matchInputData.getUser()).matchOtherPlaylist();
//        if (matchedUsers.isEmpty()) {
//            matchPresenter.prepareFailView("Unable to find Matches, please try again later.");
//        }
//        else {
//            MatchOutPutData matchOutPutData = new MatchOutPutData(true, matchedUsers);
//            matchPresenter.prepareSuccessView(matchOutPutData);
//        }
    }
}
