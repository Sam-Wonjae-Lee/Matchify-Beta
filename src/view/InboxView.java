package view;

import interface_adapter.inbox.InboxViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InboxView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "inbox";

    private final InboxViewModel inboxViewModel;

    public InboxView(InboxViewModel inboxViewModel) {
        this.inboxViewModel = inboxViewModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
