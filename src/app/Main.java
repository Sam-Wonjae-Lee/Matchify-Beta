package app;

import data_access.FileUserDataAccessObject;
import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.CommonUserFactory;
// Import all view models
import interface_adapter.decline_invite.DeclineController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.match.MatchViewModel;
import interface_adapter.ViewManagerModel;
// Import all user data access interface
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.home_page.HomePageUserDataAccessInterface;
import use_case.login.LoginSpotifyAPIDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.match.MatchUserAccessInterface;
import use_case.match.MatchSpotifyAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteUserDataAccessInterface;
// Import all view
import view.HomePageView;
import view.InboxView;
import view.LoginView;
import view.MatchView;
import view.ProfilePic;
import view.ViewManager;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //  Build main program window
        JFrame application = new JFrame("Matchify");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // View objects. One view at a time
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Keep track and manage which view is showing currently
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomePageViewModel homePageViewModel = new HomePageViewModel();
        InboxViewModel inboxViewModel = new InboxViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        MatchViewModel matchViewModel = new MatchViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SpotifyApiCallGetInfoDataAccessObject spotifyAPIDataAccessInterface;
        spotifyAPIDataAccessInterface = new SpotifyApiCallGetInfoDataAccessObject();


        HomePageView homePageView = HomePageFactory.create(viewManagerModel, homePageViewModel, matchViewModel, inboxViewModel, userDataAccessObject, spotifyAPIDataAccessInterface, userDataAccessObject, userDataAccessObject, spotifyAPIDataAccessInterface);
        views.add(homePageView, homePageView.viewName);

        InboxView inboxView = InboxFactory.create(viewManagerModel, inboxViewModel, userDataAccessObject, userDataAccessObject);
        views.add(inboxView, inboxView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, homePageViewModel, userDataAccessObject, spotifyAPIDataAccessInterface);
        views.add(loginView, loginView.viewName);

        MatchView matchView = MatchFactory.create();
        views.add(matchView, matchView.viewName);

        ProfilePic profilePic = new ProfilePic();

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
