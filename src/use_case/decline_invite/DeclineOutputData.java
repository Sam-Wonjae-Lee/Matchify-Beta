package use_case.decline_invite;

public class DeclineOutputData {

    private final String deletedInvite;


    public DeclineOutputData(String deletedInvite) {
        this.deletedInvite = deletedInvite;
    }

    public String getDeletedInvite(){
        return deletedInvite;
    }
}
