package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchPresenter;
import interface_adapter.match.MatchViewModel;
import interface_adapter.open_inbox.OpenInboxController;
import interface_adapter.open_inbox.OpenInboxPresenter;
import use_case.match.*;
import use_case.open_inbox.OpenInboxInputBoundary;
import use_case.open_inbox.OpenInboxInteractor;
import use_case.open_inbox.OpenInboxOutputBoundary;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import view.HomePageView;

import javax.swing.*;
import java.io.IOException;

public class HomePageFactory {
    private HomePageFactory() {
    }

    public static HomePageView create(
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            MatchViewModel matchViewModel,
            InboxViewModel inboxViewModel,
            MatchUserAccessInterface matchUserAccessInterface,
            OpenInboxUserDataAccessInterface openInboxUserDataAccessInterface,
            MatchSpotifyAccessInterface matchSpotifyAccessInterface) {

        try {
            MatchController matchController = createMatchUseCase(
                    viewManagerModel,
                    matchViewModel,
                    matchUserAccessInterface,
                    matchSpotifyAccessInterface);
            OpenInboxController openInboxController = createOpenInboxUseCase(
                    viewManagerModel,
                    inboxViewModel,
                    openInboxUserDataAccessInterface);
            return new HomePageView(homePageViewModel, matchController, openInboxController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file. ");
        }

        return null;
    }

    private static MatchController createMatchUseCase(
            ViewManagerModel viewManagerModel,
            MatchViewModel matchViewModel,
            MatchUserAccessInterface userDataAccessObject,
            MatchSpotifyAccessInterface spotifyAccessObject) throws IOException {

        MatchOutputBoundary matchOutputBoundary = new MatchPresenter(matchViewModel, viewManagerModel);

        MatchInputboundary matchInteractor = new MatchInteractor(
                matchOutputBoundary,
                userDataAccessObject,
                spotifyAccessObject);

        return new MatchController(matchInteractor);

    }

    private static OpenInboxController createOpenInboxUseCase(
            ViewManagerModel viewManagerModel,
            InboxViewModel inboxViewModel,
            OpenInboxUserDataAccessInterface userDataAccessObject) throws IOException {
        OpenInboxOutputBoundary openInboxOutputBoundary = new OpenInboxPresenter(inboxViewModel, viewManagerModel);

        OpenInboxInputBoundary openInboxInputBoundary = new OpenInboxInteractor(userDataAccessObject, openInboxOutputBoundary);

        return new OpenInboxController(openInboxInputBoundary);
    }
}
