package view;

import interface_adapter.decline_invite.DeclineController;
import interface_adapter.accept_invite.AcceptController;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class InboxView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "inbox";
    private final InboxViewModel inboxViewModel;

    private final JButton[] accept;

    private final JButton[] decline;
    private final DeclineController declineController;
    private final AcceptController acceptController;

    public InboxView(InboxViewModel inboxViewModel, DeclineController declineController, AcceptController acceptController) {

        this.declineController = declineController;
        this.acceptController = acceptController;
        this.inboxViewModel = inboxViewModel;
        this.inboxViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(inboxViewModel.getState().getUsername() + " 's Inbox");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        List<String> inbox = inboxViewModel.getState().getInbox();
        accept = new JButton[inbox.size()];
        decline = new JButton[inbox.size()];

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);

        //makes a accept and decline button for every user and adds to the view.
        for(int i = 0; i < accept.length; i++){
            String inviter_id = inbox.get(i);
            accept[i] = new JButton(InboxViewModel.ACCEPT_BUTTON_LABEL);
            decline[i] = new JButton(InboxViewModel.DECLINE_BUTTON_LABEL);
            JPanel invite = new JPanel();
            invite.add(new JLabel(inbox.get(i)));
            invite.add(accept[i]);
            invite.add(decline[i]);
            accept[i].addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            InboxState currState = inboxViewModel.getState();
                            acceptController.execute(currState.getUser_id(), inviter_id);
                        }
                    }
            );
            decline[i].addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            InboxState currState = inboxViewModel.getState();
                            declineController.execute(currState.getUser_id(), inviter_id);
                        }
                    }
            );
            this.add(invite);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InboxState state = (InboxState) evt.getNewValue();
    }
}
