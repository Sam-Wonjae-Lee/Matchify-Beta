package use_case.home_page;

public class HomePageInputData {

    final private String user_id;


    public HomePageInputData(String userId) {
        user_id = userId;
    }

    String getUser_id(){
        return user_id;
    }
}
