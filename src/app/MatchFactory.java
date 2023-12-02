package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchPresenter;
import interface_adapter.match.MatchViewModel;
import use_case.match.*;
import view.MatchView;

import javax.swing.*;
import java.io.IOException;

public class MatchFactory {

    public static MatchView create(ViewManagerModel viewManagerModel, MatchViewModel matchViewModel, HomePageViewModel homePageViewModel, MatchDataAccessInterface matchDataAccessInterface) throws IOException {

        MatchController matchController = createMatchController(viewManagerModel, matchViewModel, matchDataAccessInterface);
//        HomePageFactory homePageFactory = new HomePageFactory();
//        HomePageController homePageController =
        return new MatchView(matchViewModel, matchController, homePageViewModel);
    }
    private static MatchController createMatchController(ViewManagerModel viewManagerModel, MatchViewModel matchViewModel, MatchDataAccessInterface matchDataAccessInterface) {

        MatchOutputBoundary presenter = new MatchPresenter(matchViewModel, viewManagerModel);

        MatchInputobundary userMatchInteracter = new MatchInteractor(presenter, matchDataAccessInterface);

        return new MatchController(userMatchInteracter);
    }

}

