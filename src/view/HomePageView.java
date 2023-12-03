package view;

import java.util.List;
import entity.Playlist;
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
    public final String viewName = "main page";
    private final HomePageViewModel homePageViewModel;
    private MatchController matchController;
    private OpenInboxController openInboxController;
    private final JButton match;
    private final JButton inbox;

    public HomePageView(HomePageViewModel homePageViewModel,
                        MatchController matchController,
                        OpenInboxController openInboxController) {
        this.homePageViewModel = homePageViewModel;
        this.matchController = matchController;
        this.openInboxController = openInboxController;
        homePageViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(HomePageViewModel.TITLE_LABEL + homePageViewModel.getState().getUserName());

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        List<String> friendsList = homePageViewModel.getState().getFriendlist();

        JPanel buttons = new JPanel();
        match = new JButton(HomePageViewModel.MATCH_BUTTON_LABEL);
        buttons.add(match);
        inbox = new JButton(HomePageViewModel.INBOX_BUTTON_LABEL);
        buttons.add(inbox);

        JPanel friends = new JPanel();
        for (String friend_id: friendsList){
            friends.add(new JLabel(friend_id));
        }

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

                    public void actionPerformed(ActionEvent e) {
                        HomePageState state = homePageViewModel.getState();
                        openInboxController.execute(state.getUserID());
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(friends);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
    public void propertyChange(PropertyChangeEvent evt){
        HomePageState state = (HomePageState) evt.getNewValue();
    }
}
