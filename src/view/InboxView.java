package view;

import interface_adapter.decline_invite.DeclineController;
import interface_adapter.decline_invite.DeclineState;
import interface_adapter.decline_invite.DeclineViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InboxView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "inbox";

    private final InboxViewModel inboxViewModel;
}
