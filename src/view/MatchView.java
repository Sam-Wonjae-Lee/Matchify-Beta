package view;

import interface_adapter.home_page.HomePageController;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchState;
import interface_adapter.match.MatchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MatchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Matches";

    private final MatchViewModel matchViewModel;
    private final MatchController matchController;
    private final HomePageViewModel homePageViewModel;
    private final HomePageController homePageController;

//  Buttons
    private final JButton Back;
    private final JButton Follow;


    public MatchView(MatchViewModel matchViewModel, MatchController matchController, HomePageViewModel homePageViewModel, HomePageController homePageController) {
        this.matchViewModel = matchViewModel;
        this.matchController = matchController;
        this.homePageViewModel = homePageViewModel;
        this.homePageController = homePageController;
//      Makes matchViewModel a listener
        matchViewModel.addPropertyChangeListener(this);

        //      Title
        JLabel title = new JLabel(MatchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//      This is a collection of buttons
        JPanel buttons = new JPanel();

//      Follow Button
        Follow = new JButton(MatchViewModel.FOLLOW_BUTTON_LABEL);
        buttons.add(Follow);

//      Back Button
        Back = new JButton(MatchViewModel.BACK_BUTTON_LABEL);
        buttons.add(Back);

        Follow.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent followButton) {
//                        check if the button was pushed
                        if (followButton.getSource().equals(Follow)) {
//                            ADD SEND INVITE CONTROLLER HERE
//                            UPDATE MATCH VIEW
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