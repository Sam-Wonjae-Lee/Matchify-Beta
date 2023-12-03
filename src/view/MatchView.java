package view;

import entity.User;
import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
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
    public final String viewName = "Matches";

    private final HomePageViewModel homePageViewModel;
    private final HomePageController homePageController;
    private final MatchViewModel matchViewModel;
    private final MatchController matchController;
    private final SendInviteController sendInviteController;

//  Buttons
    private final JButton Back;
    private final JButton Invite_1;
    private final JButton Invite_2;
    private final JButton Invite_3;


    public MatchView(MatchViewModel matchViewModel, MatchController matchController, HomePageViewModel homePageViewModel, HomePageController homePageController, SendInviteController sendInviteController) {
        this.matchViewModel = matchViewModel;
        this.matchController = matchController;
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
        this.sendInviteController = sendInviteController;

//      Makes matchViewModel a listener
        matchViewModel.addPropertyChangeListener(this);

        //      Title
        JLabel title = new JLabel(MatchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//      This is a collection of buttons
        JPanel buttons = new JPanel();

//      Invite Buttons
        Invite_1 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL1);
        Invite_2 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL2);
        Invite_3 = new JButton(MatchViewModel.INVITE_BUTTON_LABEL3);
        buttons.add(Invite_1);
        buttons.add(Invite_2);
        buttons.add(Invite_3);

//      Back Button
        Back = new JButton(MatchViewModel.BACK_BUTTON_LABEL);
        buttons.add(Back);

//      Matched Users
        List<User> listUsers = matchViewModel.getState().getMatchedUsers();
        User matched_user1 = listUsers.get(0);
        User matched_user2 = listUsers.get(1);
        User matched_user3 = listUsers.get(2);


        Invite_1.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Invite_1)) {
//                            TODO: ADD SEND INVITE CONTROLLER HERE FOR matched_user1
                        }
                    }
                }
        );

        Invite_2.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Invite_2)) {
//                            TODO: ADD SEND INVITE CONTROLLER HERE FOR matched_user2
                        }
                    }
                }
        );

        Invite_3.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Invite_3)) {
//                            TODO: ADD SEND INVITE CONTROLLER HERE FOR matched_user3
                        }
                    }
                }
        );

        Back.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent backButton) {
                        if (backButton.getSource().equals(Back)) {
//                          USE OTHER CONTROLLER TO BRING BACK TO LoggedInView
                            homePageController.execute();
                        }
                    }
                }
        );

        // Add components to the panel
        this.add(buttons);
        this.add(title);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
//      This is for a button that has not been implemented
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MatchState state = (MatchState) evt.getNewValue();
//      See if there's an error with MatchedUsers
        if (state.getMatchedUsersError() != null) {
//          Display Error Screen
            JOptionPane.showMessageDialog(this, state.getMatchedUsersError());
        }
    }
}