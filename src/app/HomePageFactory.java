package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePagePresenter;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.match.MatchViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.open_inbox.OpenInboxController;
import use_case.home_page.HomePageInputBoundary;
import use_case.home_page.HomePageInteracter;
import use_case.home_page.HomePageOutData;
import use_case.home_page.HomePageOutPutBoundary;
import use_case.login.LoginInputBoundary;
import view.HomePageView;

import javax.swing.*;
import java.io.IOException;

public class HomePageFactory {
    private HomePageFactory() {}

    public static HomePageView create (
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            MatchViewModel matchViewModel,
            InboxViewModel inboxViewModel,
            MatchController matchController,
            OpenInboxController openInboxController) {

        try {
            HomePageController homePageController = createHomePageUseCase(viewManagerModel, homePageViewModel, matchViewModel, inboxViewModel);
            return new HomePageView(homePageViewModel, matchController, openInboxController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file. ");
        }

        return null;
    }

    private static HomePageController createHomePageUseCase (
            ViewManagerModel viewManagerModel,
            HomePageViewModel homePageViewModel,
            MatchViewModel matchViewModel,
            InboxViewModel inboxViewModel,
            MatchController matchController,
            OpenInboxController openInboxController) throws IOException {

        HomePageOutPutBoundary homePageOutPutBoundary = new HomePagePresenter(homePageViewModel, viewManagerModel);

        UserFactory userFactory = new CommonUserFactory();

        HomePageInputBoundary homepageInteractor = new HomePageInteracter(
                homePageOutPutBoundary);

        return new HomePageController(homepageInteractor);

    }
}
