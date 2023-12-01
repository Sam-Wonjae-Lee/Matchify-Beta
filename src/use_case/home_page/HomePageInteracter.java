package use_case.home_page;


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
