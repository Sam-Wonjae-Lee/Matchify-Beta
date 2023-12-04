package view;

import data_access.SpotifyApiCallGetInfoDataAccessObject;
import entity.User;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.inbox.InboxState;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchState;
import interface_adapter.match.MatchViewModel;
import interface_adapter.send_invite.SendInviteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MatchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "matches";

    private final MatchViewModel matchViewModel;

    JButton[] send_invite;

    JPanel matches;

    private final JButton back;

    private final JButton match;
    private final HomePageController homePageController;
    private final MatchController matchController;
    private final SendInviteController sendInviteController;


    public MatchView(MatchViewModel matchViewModel, MatchController matchController, HomePageViewModel homePageViewModel, HomePageController homePageController, SendInviteController sendInviteController) {
        this.matchViewModel = matchViewModel;
        this.matchController = matchController;
        this.homePageController = homePageController;
        this.sendInviteController = sendInviteController;

//      Makes matchViewModel a listener
        matchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Matches for you");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        match = new JButton("Match");
        back = new JButton("Back");
        buttons.add(match);
        buttons.add(back);
        matches = new JPanel();
        matches.setLayout(new BoxLayout(matches, BoxLayout.Y_AXIS));
        matches.setBorder(BorderFactory.createLoweredBevelBorder());
        matches.setAutoscrolls(true);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        MatchState state = matchViewModel.getState();
                        homePageController.execute(state.getUser_id());
                    }
                }
        );

        match.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        HomePageState state = homePageViewModel.getState();
                        matchController.execute(state.getUserID());
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(new JScrollPane(matches));
        this.add(buttons);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MatchState state = (MatchState) evt.getNewValue();
//      See if there's an error with MatchedUsers
        if (state.getMatchError() != null) {
//          Display Error Screen
            JOptionPane.showMessageDialog(this, state.getMatchError());
        }
        match.removeAll();
        List<String> matched_users = state.getMatched_users();
        send_invite = new JButton[matched_users.size()];
        for(int i = 0; i < send_invite.length; i++) {
            String friend_id = matched_users.get(i);
            send_invite[i] = new JButton(MatchViewModel.SEND_INVITE_BUTTON_LABEL);
            JPanel add_friend = new JPanel();
            add_friend.setBorder(BorderFactory.createRaisedBevelBorder());
            add_friend.setAlignmentX(Component.CENTER_ALIGNMENT);
            add_friend.add(new JLabel(matched_users.get(i)));
            add_friend.add(send_invite[i]);
            send_invite[i].addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            MatchState currState = matchViewModel.getState();
                            System.out.println("clicked add friend");
                            System.out.println("===");
                            System.out.println("id 1: " + currState.getUser_id());
                            System.out.println("id 2: " + friend_id);
                            System.out.println("===");
                            sendInviteController.execute(currState.getUser_id(), friend_id);
                            updatePanel();
                        }
                    }
            );
        }
    }
    private void updatePanel(){
        matches.revalidate();
        matches.repaint();
    }
}