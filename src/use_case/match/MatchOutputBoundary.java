package use_case.match;

public interface MatchOutputBoundary {
    void prepareSuccessView(MatchOutPutData userList);

    void prepareFailView(String error);
}
