package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData loginOutputData);

    void prepareFailView(String error);

}
