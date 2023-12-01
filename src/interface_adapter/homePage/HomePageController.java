package interface_adapter.homePage;

import use_case.homePage.HomePageInputBoundary;

public class HomePageController {
    HomePageInputBoundary homePageInteractor;

    HomePageController(HomePageInputBoundary homePageInputBoundary) {
        this.homePageInteractor = homePageInputBoundary;
    }

    public void execute() {
        this.homePageInteractor.execute();
    }

}
