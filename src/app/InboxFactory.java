package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.accept_invite.AcceptController;
import interface_adapter.accept_invite.AcceptPresenter;
import interface_adapter.decline_invite.DeclineController;
import interface_adapter.decline_invite.DeclinePresenter;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePagePresenter;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import use_case.accept_invite.AcceptInteractor;
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineInteractor;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.home_page.*;
import view.InboxView;

import javax.swing.*;
import java.io.IOException;

public class InboxFactory {

    private InboxFactory() {}

    public static InboxView create(
        ViewManagerModel viewManagerModel,
        InboxViewModel inboxViewModel,
        AcceptUserDataAccessInterface acceptUserDataAccessInterface,
        DeclineUserDataAccessInterface declineUserDataAccessInterface,
        HomePageViewModel homePageViewModel,
        HomePageUserDataAccessInterface homePageUserDataAccessInterface,
        HomePageSpotifyAPIDataAccessInterface homePageSpotifyAPIDataAccessObject) {

        try {
            HomePageController homePageController = createHomePageController(viewManagerModel, homePageViewModel,
                    homePageUserDataAccessInterface, homePageSpotifyAPIDataAccessObject);
            AcceptPresenter acceptPresenter = new AcceptPresenter();
            AcceptInteractor acceptInteractor = new AcceptInteractor(acceptUserDataAccessInterface, acceptPresenter);
            AcceptController acceptController = new AcceptController(acceptInteractor);
            DeclinePresenter declinePresenter = new DeclinePresenter(inboxViewModel);
            DeclineInteractor declineInteractor = new DeclineInteractor(declineUserDataAccessInterface, declinePresenter);
            DeclineController declineController = new DeclineController(declineInteractor);
            return new InboxView(inboxViewModel, declineController, acceptController, homePageController);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }
    private static HomePageController createHomePageController(
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            HomePageUserDataAccessInterface userDataAccessInterface,
            HomePageSpotifyAPIDataAccessInterface spotifyAPIDataAccessObject) throws IOException{

        HomePageOutPutBoundary homePagePresenter = new HomePagePresenter(homePageViewModel, viewManagerModel);

        HomePageInputBoundary homePageInteractor = new HomePageInteractor(
                homePagePresenter, spotifyAPIDataAccessObject, userDataAccessInterface);

        return new HomePageController(homePageInteractor);
    }
}
