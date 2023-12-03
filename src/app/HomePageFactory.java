package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePagePresenter;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchPresenter;
import interface_adapter.match.MatchViewModel;
import interface_adapter.open_inbox.OpenInboxController;
import interface_adapter.open_inbox.OpenInboxPresenter;
import use_case.home_page.*;
import use_case.match.MatchInteractor;
import use_case.match.MatchOutputBoundary;
import use_case.match.MatchSpotifyAccessInterface;
import use_case.match.MatchUserAccessInterface;
import use_case.open_inbox.OpenInboxInputBoundary;
import use_case.open_inbox.OpenInboxInteractor;
import use_case.open_inbox.OpenInboxOutputBoundary;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import view.HomePageView;
import view.InboxView;

import javax.swing.*;
import java.io.IOException;

public class HomePageFactory {
    private HomePageFactory() {}

    public static HomePageView create (
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            MatchViewModel matchViewModel,
            InboxViewModel inboxViewModel,
            HomePageUserDataAccessInterface homePageUserDataAccessInterface,
            HomePageSpotifyAPIDataAccessInterface homePageSpotifyAPIDataAccessInterface,
            MatchUserAccessInterface matchUserAccessInterface,
            OpenInboxUserDataAccessInterface openInboxUserDataAccessInterface,
            MatchSpotifyAccessInterface matchSpotifyAccessInterface) {

        try {
            HomePageController homePageController = createHomePageUseCase(viewManagerModel, homePageViewModel, homePageSpotifyAPIDataAccessInterface, homePageUserDataAccessInterface);
            MatchPresenter matchPresenter = new MatchPresenter(matchViewModel, viewManagerModel);
            MatchInteractor matchInteractor = new MatchInteractor(matchPresenter, matchUserAccessInterface, matchSpotifyAccessInterface);
            MatchController matchController = new MatchController(matchInteractor);
            OpenInboxPresenter openInboxPresenter = new OpenInboxPresenter(inboxViewModel, viewManagerModel);
            OpenInboxInteractor openInboxInteractor = new OpenInboxInteractor(openInboxUserDataAccessInterface, openInboxPresenter);
            OpenInboxController openInboxController = new OpenInboxController(openInboxInteractor);
            return new HomePageView(homePageViewModel, matchController, openInboxController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file. ");
        }

        return null;
    }

    private static HomePageController createHomePageUseCase (
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            HomePageSpotifyAPIDataAccessInterface homePageSpotifyAPIDataAccessInterface,
            HomePageUserDataAccessInterface userDataAccessInterface) throws IOException {

        HomePageOutPutBoundary homePageOutPutBoundary = new HomePagePresenter(homePageViewModel, viewManagerModel);

        UserFactory userFactory = new CommonUserFactory();

        HomePageInputBoundary homepageInteractor = new HomePageInteractor(
                homePageOutPutBoundary, homePageSpotifyAPIDataAccessInterface, userDataAccessInterface
        );

        return new HomePageController(homepageInteractor);

    }
}
