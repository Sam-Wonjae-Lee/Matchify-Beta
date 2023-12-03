package use_case;

import use_case.match.MatchInputData;
import use_case.match.MatchInputboundary;

public class MatchTest {

    String userID = "123";
    MatchInputData matchInputData = new MatchInputData(userID);

    void successTest() {

    }

}
//void successTest() {
//SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
//SignupOutputBoundary successPresenter = // Make a presenter here that asserts things
//SignupInputData inputData = new SignupInputData("Paul", "password", "password");
//SignupInputBoundary interactor = new SignupInteractor(
//userRepository, successPresenter, new CommonUserFactory());
//interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
//}
//17