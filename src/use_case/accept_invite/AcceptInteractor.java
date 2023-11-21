package use_case.accept_invite;

public class AcceptInteractor {

    final AcceptUserDataAccessInterface userDataAccessObject;

    final AcceptOutputBoundary acceptPresenter;

    public AcceptInteractor(AcceptUserDataAccessInterface userDataAccessObject, AcceptOutputBoundary acceptPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.acceptPresenter = acceptPresenter;
    }

//    public void execute() { acceptPresenter.prepareView(userDataAccessObject.add()); }
}
