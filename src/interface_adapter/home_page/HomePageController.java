package interface_adapter.home_page;

import use_case.home_page.HomePageInputBoundary;
import use_case.home_page.HomePageInputData;

public class HomePageController {
    HomePageInputBoundary homePageInteractor;

    public HomePageController(HomePageInputBoundary homePageInputBoundary) {
        this.homePageInteractor = homePageInputBoundary;
    }

    public void execute(String user_id) {
        HomePageInputData inputData = new HomePageInputData(user_id);
        this.homePageInteractor.execute(inputData);
    }

}
