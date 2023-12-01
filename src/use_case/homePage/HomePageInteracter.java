package use_case.homePage;


public class HomePageInteracter implements HomePageInputBoundary{

    public HomePageOutPutBoundary presenter;

    public HomePageInteracter(HomePageOutPutBoundary homePageOutPutBoundary) {
        this.presenter = homePageOutPutBoundary;
    }

    @Override
    public void execute() {
        presenter.prepareSuccessView();
    }
}
