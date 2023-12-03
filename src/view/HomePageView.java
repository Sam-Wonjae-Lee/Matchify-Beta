package view;

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
    public final String viewName = "logged in";
    private final HomePageViewModel loggedInViewModel;
    private MatchController matchController;
    private OpenInboxController openInboxController;
    JLabel username;
    private final JButton Find_Matches;
    private final JButton Inbox;
    private final JButton Friends_List;
    public HomePageView(HomePageViewModel homeInViewModel, MatchController matchController, OpenInboxController openInboxController) {
        this.loggedInViewModel = homeInViewModel;
        this.matchController = matchController;
        this.openInboxController = openInboxController;

        JPanel buttons = new JPanel();
//      username title
//      TODO: fix this username thing later
        username = new JLabel();
//      Find matches button
        Find_Matches = new JButton(HomePageViewModel.Find_Matches_Label);
        buttons.add(Find_Matches);
        Inbox = new JButton(HomePageViewModel.Inbox_Label);
        buttons.add(Inbox);
        Friends_List = new JButton(HomePageViewModel.Friend_List_Label);
        buttons.add(Friends_List);
//      Title
        JLabel title = new JLabel(homeInViewModel.getViewName());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//      Profile pic replace url later with API
        ProfilePic.displayImageFrame("https://example.com/image.jpg");

//      Match button listener
        Find_Matches.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent findMatchesButton) {
                        if (findMatchesButton.getSource().equals(Find_Matches)) {
                            HomePageState homePageState = homeInViewModel.getState();
//                          Put the user that pressed match in the parameter
                            matchController.execute(homePageState.getUserID());
                        }
                    }
                }
        );

//      Inbox Button
        Inbox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent inboxButton) {
                        if (inboxButton.getSource().equals(Inbox)) {
                            HomePageState homePageState = homeInViewModel.getState();
                            openInboxController.execute(homePageState.getUserName());
                        }
                    }
                }
        );

        this.add(username);
        this.add(title);
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
