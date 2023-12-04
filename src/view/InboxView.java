package view;

import interface_adapter.decline_invite.DeclineController;
import interface_adapter.accept_invite.AcceptController;
import interface_adapter.home_page.HomePageController;
import interface_adapter.inbox.InboxState;
import interface_adapter.inbox.InboxViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class InboxView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "inbox";
    private final InboxViewModel inboxViewModel;

    JButton[] accept;

    JButton[] decline;

    private final JButton back;
    private final DeclineController declineController;
    private final AcceptController acceptController;

    JPanel inbox;
    private final HomePageController homePageController;

    JLabel username;

    public InboxView(InboxViewModel inboxViewModel,
                     DeclineController declineController,
                     AcceptController acceptController,
                     HomePageController homePageController) {

        this.declineController = declineController;
        this.acceptController = acceptController;
        this.inboxViewModel = inboxViewModel;
        this.homePageController = homePageController;
        this.inboxViewModel.addPropertyChangeListener(this);

        JPanel title = new JPanel();
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usernameInfo = new JLabel("'s inbox");
        username = new JLabel();
        title.add(username);
        title.add(usernameInfo);
        back = new JButton("Back");
        inbox = new JPanel();
        inbox.setLayout(new BoxLayout(inbox, BoxLayout.Y_AXIS));
        inbox.setBorder(BorderFactory.createLoweredBevelBorder());
        inbox.setAutoscrolls(true);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(new JScrollPane(inbox));

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        InboxState state = inboxViewModel.getState();
                        homePageController.execute(state.getUser_id());
                    }
                }
        );
        JPanel buttons = new JPanel();
        buttons.add(back);
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
        List<String> userInbox = state.getInbox();
        inbox.removeAll();
        accept = new JButton[userInbox.size()];
        decline = new JButton[userInbox.size()];
        for(int i = 0; i < accept.length; i++){
            String inviter_id = userInbox.get(i);
            accept[i] = new JButton(InboxViewModel.ACCEPT_BUTTON_LABEL);
            decline[i] = new JButton(InboxViewModel.DECLINE_BUTTON_LABEL);
            JPanel invite = new JPanel();
            invite.setBorder(BorderFactory.createRaisedBevelBorder());
            invite.setAlignmentX(Component.CENTER_ALIGNMENT);
            invite.add(new JLabel(userInbox.get(i)));
            invite.add(accept[i]);
            invite.add(decline[i]);
            accept[i].addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            InboxState currState = inboxViewModel.getState();
                            System.out.println("clicked accept");
                            System.out.println("===");
                            System.out.println("id 1: " + currState.getUser_id());
                            System.out.println("id 2: " + inviter_id);
                            System.out.println("===");
                            acceptController.execute(currState.getUser_id(), inviter_id);
                            updatePanel();
                        }
                    }
            );
            decline[i].addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            InboxState currState = inboxViewModel.getState();
                            System.out.println("clicked decline");
                            System.out.println("===");
                            System.out.println("id 1: " + currState.getUser_id());
                            System.out.println("id 2: " + inviter_id);
                            System.out.println("===");
                            declineController.execute(currState.getUser_id(), inviter_id);
                            updatePanel();
                        }
                    }
            );
            inbox.add(invite);
        }
    }

    private void updatePanel(){
        inbox.revalidate();
        inbox.repaint();
    }
}
