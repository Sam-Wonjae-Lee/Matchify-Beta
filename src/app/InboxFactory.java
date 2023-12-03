package app;

import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.accept_invite.AcceptController;
import interface_adapter.accept_invite.AcceptPresenter;
import interface_adapter.decline_invite.DeclineController;
import interface_adapter.decline_invite.DeclinePresenter;
import interface_adapter.inbox.InboxViewModel;
import interface_adapter.open_inbox.OpenInboxController;
import interface_adapter.open_inbox.OpenInboxPresenter;
import use_case.accept_invite.AcceptInteractor;
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineInteractor;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.open_inbox.OpenInboxInputBoundary;
import use_case.open_inbox.OpenInboxInteractor;
import use_case.open_inbox.OpenInboxOutputBoundary;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import view.InboxView;

import javax.swing.*;
import javax.swing.text.View;
import java.io.IOException;

public class InboxFactory {

    private InboxFactory() {}

    public static InboxView create(
            InboxViewModel inboxViewModel,
            AcceptUserDataAccessInterface acceptUserDataAccessInterface,
            DeclineUserDataAccessInterface declineUserDataAccessInterface) {

        AcceptPresenter acceptPresenter = new AcceptPresenter();
        AcceptInteractor acceptInteractor = new AcceptInteractor(acceptUserDataAccessInterface, acceptPresenter);
        AcceptController acceptController = new AcceptController(acceptInteractor);
        DeclinePresenter declinePresenter = new DeclinePresenter();
        DeclineInteractor declineInteractor = new DeclineInteractor(declineUserDataAccessInterface, declinePresenter);
        DeclineController declineController = new DeclineController(declineInteractor);
        return new InboxView(inboxViewModel, declineController, acceptController);

    }

}
