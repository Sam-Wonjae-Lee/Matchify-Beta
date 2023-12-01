package interface_adapter.logged_in;

import use_case.HomePage.HomePageInputBoundary;
import use_case.HomePage.HomePageInteracter;

public class LoggedInController {
    HomePageInputBoundary homePageInteractor;

    LoggedInController(HomePageInputBoundary homePageInputBoundary) {
        this.homePageInteractor = homePageInputBoundary;
    }

    public void execute() {
        this.homePageInteractor.execute();
    }

}
