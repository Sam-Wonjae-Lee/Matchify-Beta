package view;

import interface_adapter.logged_in.LoggedInViewModel;
import view.ProfilePic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    JLabel username;
    private final JButton Find_Matches;
    public LoggedInView (LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        JPanel buttons = new JPanel();
//      username title
        username = new JLabel();
//      Find matches button
        Find_Matches = new JButton(LoggedInViewModel.Find_Matches_Label);
        buttons.add(Find_Matches);
//      Title
        JLabel title = new JLabel();
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//      Profile pic replace url later with API
        ProfilePic.displayImageFrame("https://example.com/image.jpg");


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
