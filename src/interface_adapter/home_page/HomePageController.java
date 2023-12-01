package interface_adapter.home_page;

import use_case.home_page.HomePageInputBoundary;

public class HomePageController {
    HomePageInputBoundary homePageInteractor;

    HomePageController(HomePageInputBoundary homePageInputBoundary) {
        this.homePageInteractor = homePageInputBoundary;
    }

    public void execute() {
        this.homePageInteractor.execute();
    }

}
