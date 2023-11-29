package use_case.Match;

public interface MatchOutputBoundary {
    void prepareSuccessView(MatchOutPutData userList);

    void prepareFailView(String error);
}
