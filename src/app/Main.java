package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
// import all view models
import interface_adapter.decline_invite.DeclineController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.match.MatchViewModel;
import interface_adapter.ViewManagerModel;
// import all user data access interface
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.login.LoginSpotifyAPIDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.match.MatchDataAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteUserDataAccessInterface;
// import all view
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

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Matchify");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomePageViewModel homePageViewModel = new HomePageViewModel();
        InboxViewModel inboxViewModel = new InboxViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        MatchViewModel matchViewModel = new MatchViewModel();

        FileUserDataAccessObject userDataAccessObject;
        /*
        try {
            // however we store it idk frank you do dis userDataAccessObject = new FileUserDataAccessObject()
        } catch (IOException e) {
            throw new RuntimeException(e);
            */

        HomePageView homePageView = HomePageFactory.create();
        views.add(homePageView, homePageView.viewName);

        InboxView inboxView = InboxFactory.create();
        views.add(inboxView, inboxView.viewName);

        LoginView loginView = LoginUseCaseFactory.create();
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
