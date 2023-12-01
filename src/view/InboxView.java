package view;

import interface_adapter.decline_invite.DeclineController;
import interface_adapter.accept_invite.AcceptController;
import interface_adapter.inbox.InboxController;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InboxView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "inbox";

    JLabel username;
    private final InboxViewModel inboxViewModel;
    private final DeclineController declineController;
    private final AcceptController acceptController;
    private final JButton decline;
    private final JButton accept;

    public InboxView(InboxController controller, InboxViewModel inboxViewModel, DeclineController declineController, AcceptController acceptController) {

        this.inboxController = controller;
        this.inboxViewModel = inboxViewModel;
        this. declineController = declineController;
        this.acceptController = acceptController;
        inboxViewModel.addPropertyChangeListener(this);

        JLabel username = new JLabel(inboxViewModel.USERNAME_LABEL);

        JPanel buttons = new JPanel();
        decline = new JButton(InboxViewModel.DECLINE_BUTTON_LABEL);
        buttons.add(decline);
        accept = new JButton(InboxViewModel.ACCEPT_BUTTON_LABEL);
        buttons.add(accept);

        decline.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(decline)) {
                            InboxState currentState = inboxViewModel.getState();
                        }
                    }
                }
        );

        accept.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(accept)) {
                            InboxState currentState = inboxViewModel.getState();

                            acceptController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        this.add(username);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InboxState state = (InboxState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}
