package app;

import data_access.FileUserDataAccessObject;
import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.CommonUserFactory;
// Import all view models
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.match.MatchViewModel;
import interface_adapter.ViewManagerModel;
// Import all user data access interface
// Import all view
import view.HomePageView;
import view.InboxView;
import view.LoginView;
import view.MatchView;
import view.ViewManager;

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


        HomePageView homePageView = HomePageFactory.create(
                viewManagerModel, homePageViewModel,
                matchViewModel, inboxViewModel,
                userDataAccessObject, userDataAccessObject,
                spotifyAPIDataAccessInterface,
                loginViewModel);

        views.add(homePageView, homePageView.viewName);

        InboxView inboxView = InboxFactory.create(viewManagerModel, inboxViewModel, userDataAccessObject,
                userDataAccessObject, homePageViewModel, userDataAccessObject, spotifyAPIDataAccessInterface);
        views.add(inboxView, inboxView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, homePageViewModel, userDataAccessObject, spotifyAPIDataAccessInterface);
        views.add(loginView, loginView.viewName);

        MatchView matchView = MatchFactory.create(viewManagerModel, matchViewModel,homePageViewModel, userDataAccessObject, spotifyAPIDataAccessInterface, userDataAccessObject, spotifyAPIDataAccessInterface, userDataAccessObject);
        views.add(matchView, matchView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}
