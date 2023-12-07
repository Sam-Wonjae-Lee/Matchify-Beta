package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.match.MatchController;
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

    private final LoginViewModel loginViewModel;
    private MatchController matchController;
    private OpenInboxController openInboxController;

    private final ViewManagerModel viewManagerModel;
    private final JButton match;
    private final JButton inbox;

    private final JButton logout;

    JPanel friends;

    JLabel username;

    public HomePageView(HomePageViewModel homePageViewModel,
                        LoginViewModel loginViewModel, MatchController matchController,
                        OpenInboxController openInboxController, ViewManagerModel viewManagerModel) {
        this.homePageViewModel = homePageViewModel;
        this.loginViewModel = loginViewModel;
        this.matchController = matchController;
        this.openInboxController = openInboxController;
        this.viewManagerModel = viewManagerModel;
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
        logout = new JButton(HomePageViewModel.LOGUOUT_BUTTON_LABEL);
        buttons.add(logout);

        friends = new JPanel();
        friends.setLayout(new BoxLayout(friends, BoxLayout.Y_AXIS));
        friends.setBorder(BorderFactory.createLoweredBevelBorder());
        friends.setAutoscrolls(true);

        match.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        HomePageState state = homePageViewModel.getState();
                        System.out.println("in home page: " + state.getUserID());
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

        logout.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        viewManagerModel.setActiveView(loginViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
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
        friends.removeAll();
        JLabel title = new JLabel(" Friends:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        friends.add(title);
        for (String friend_id : state.getFriendlist()) {
            JPanel friend = new JPanel();
            friend.setAlignmentX(Component.CENTER_ALIGNMENT);
            friend.add(new JLabel(friend_id));
            friends.add(friend);
        }
    }
}
