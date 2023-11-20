package usecases.Match;
import entity.Playlist;
import entity.User;

import java.util.ArrayList;

public class MatchInteractor implements MatchInputobundary{
    private Playlist playlist;
    public MatchInteractor(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void execute(MatchInputData matchInputData) {
        ArrayList<User> matchedUsers = this.playlist.matchOtherPlaylist();

    }
}
