package view;

import java.util.List;
import entity.Playlist;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxState;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchState;
import interface_adapter.open_inbox.OpenInboxController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "home page";
    private final HomePageViewModel homePageViewModel;
    private MatchController matchController;
    private OpenInboxController openInboxController;
    private final JButton match;
    private final JButton inbox;

    JPanel friends;

    JLabel username;

    public HomePageView(HomePageViewModel homePageViewModel,
                        MatchController matchController,
                        OpenInboxController openInboxController) {
        this.homePageViewModel = homePageViewModel;
        this.matchController = matchController;
        this.openInboxController = openInboxController;
        homePageViewModel.addPropertyChangeListener(this);

        JPanel title = new JPanel();
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usernameInfo = new JLabel("Logged in: ");
        username = new JLabel();
        title.add(usernameInfo);
        title.add(username);

        JPanel buttons = new JPanel();
        match = new JButton(HomePageViewModel.MATCH_BUTTON_LABEL);
        buttons.add(match);
        inbox = new JButton(HomePageViewModel.INBOX_BUTTON_LABEL);
        buttons.add(inbox);
        friends = new JPanel();
        friends.setLayout(new BoxLayout(friends, BoxLayout.Y_AXIS));
        friends.setBorder(BorderFactory.createLoweredBevelBorder());
        friends.setAlignmentX(Component.CENTER_ALIGNMENT);
        friends.add(new JLabel("Friends:"));
        friends.setAutoscrolls(true);

        match.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        HomePageState state = homePageViewModel.getState();
                        matchController.execute(state.getUserID());
                    }
                }
        );

        inbox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HomePageState state = homePageViewModel.getState();
                        openInboxController.execute(state.getUserID(), state.getUserName());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JScrollPane(friends));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        HomePageState state = (HomePageState) evt.getNewValue();
        username.setText(state.getUserName());
        for (String friend_id : state.getFriendlist()) {
            JPanel friend = new JPanel();
            friend.setAlignmentX(Component.CENTER_ALIGNMENT);
            friend.add(new JLabel(friend_id));
            friends.add(friend);
        }
    }
}
