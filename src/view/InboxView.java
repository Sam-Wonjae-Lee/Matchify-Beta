package view;

import interface_adapter.decline_invite.DeclineController;
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

    private final JButton decline;

    public InboxView(DeclineController declineController, InboxViewModel inboxViewModel) {

        this.declineController = declineController;
        this.inboxViewModel = inboxViewModel;
        this.inboxViewModel.addPropertyChangeListener(this);

        JPanel buttons = new JPanel();
        decline = new JButton(InboxViewModel.DECLINE_BUTTON_LABEL);
        buttons.add(decline);

        decline.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(decline)) {
                            declineController.execute();
                        }
                    }
                }
        );

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
