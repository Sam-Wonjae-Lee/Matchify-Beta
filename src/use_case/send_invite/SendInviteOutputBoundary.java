package use_case.send_invite;

public interface SendInviteOutputBoundary {

    void prepareSuccessView(SendInviteOutputData user);

    //for possible fail conditions to be implemented in the future
    void prepareFailView(String error);
}
