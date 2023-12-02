package use_case.match;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static data_access.SpotifyApiCallGetInfoDataAccessObject.*;

public class MatchInteractor implements MatchInputobundary{
    public MatchOutputBoundary matchPresenter;
    public MatchUserAccessInterface matchUserAccessInterface;

    public MatchSpotifyAccessInterface matchSpotifyAccessInterface;

    public MatchInteractor(MatchOutputBoundary matchOutputBoundary, MatchUserAccessInterface matchUserAccessInterface, MatchSpotifyAccessInterface matchSpotifyAccessInterface) {
        this.matchPresenter = matchOutputBoundary;
        this.matchUserAccessInterface = matchUserAccessInterface;
        this.matchSpotifyAccessInterface = matchSpotifyAccessInterface;
    }

    @Override
    public void execute(MatchInputData matchInputData) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException, ParseException {
        CommonUser user = matchInputData.getUser();
        List<String> playlistIds = this.matchSpotifyAccessInterface.getPlaylistIds("31s453ebjxsfte4fzcyqkanmrlb4");
        Map<String, Integer> allGenresFrequencyMap = new HashMap<>();
        for (String playlistId : playlistIds) {
            List<String> artistIds = this.matchSpotifyAccessInterface.getArtistsIds(playlistId);

            for (String artistId : artistIds) {
                List<String> genres = this.matchSpotifyAccessInterface.getGenres(artistId);

                for (String genre : genres) {
                    allGenresFrequencyMap.put(genre, allGenresFrequencyMap.getOrDefault(genre, 0) + 1);
                }
            }
        }
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
