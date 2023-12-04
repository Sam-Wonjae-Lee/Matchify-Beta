package use_case.accept_invite;

public class AcceptOutputData {
    private final String deletedInvite;


    public AcceptOutputData(String deletedInvite) {
        this.deletedInvite = deletedInvite;
    }

    public String getDeletedInvite(){
        return deletedInvite;
    }
}

