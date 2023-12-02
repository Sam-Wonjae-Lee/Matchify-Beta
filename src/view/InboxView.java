package view;

import interface_adapter.decline_invite.DeclineController;
import interface_adapter.accept_invite.AcceptController;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InboxView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "inbox";

    private JLabel username;
    private final InboxViewModel inboxViewModel;
    private final DeclineController declineController;
    private final AcceptController acceptController;

    public InboxView(InboxViewModel inboxViewModel, DeclineController declineController, AcceptController acceptController) {

        this.inboxViewModel = inboxViewModel;
        this.declineController = declineController;
        this.acceptController = acceptController;
        inboxViewModel.addPropertyChangeListener(this);

        for (String username : inboxViewModel.getState().getInbox()) {
            Integer user_id = inboxViewModel.getState().getUser_id(username);
            Integer friend_id = inboxViewModel.getState().getFriend_id(username);
            JButton decline = new JButton("D");
            decline.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            declineController.execute(username);
                        }
                    }
            );
            this.add(decline);

            JButton accept = new JButton("A");
            accept.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            acceptController.execute(user_id, friend_id);

                        }
                    }
            );
            this.add(accept);
        }
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
