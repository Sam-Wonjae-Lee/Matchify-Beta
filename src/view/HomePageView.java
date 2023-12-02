package view;

import interface_adapter.home_page.HomePageState;
import interface_adapter.home_page.HomePageViewModel;
import interface_adapter.match.MatchController;
import interface_adapter.match.MatchState;

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
    JLabel username;
    private final JButton Find_Matches;
    public HomePageView(HomePageViewModel homeInViewModel, MatchController matchController) {
        this.loggedInViewModel = homeInViewModel;
        this.matchController = matchController;

        JPanel buttons = new JPanel();
//      username title
        username = new JLabel();
//      Find matches button
        Find_Matches = new JButton(HomePageViewModel.Find_Matches_Label);
        buttons.add(Find_Matches);
//      Title
        JLabel title = new JLabel();
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
