package view;

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

    public InboxView(InboxViewModel inboxViewModel) {
        this.inboxViewModel = inboxViewModel;
        this.inboxViewModel.addPropertyChangeListener(this);

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
