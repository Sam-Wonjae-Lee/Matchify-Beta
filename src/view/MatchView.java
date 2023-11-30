package view;

import interface_adapter.match.MatchController;
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
    private final JButton cancel;

    private final JButton Follow;


    public MatchView(MatchViewModel matchViewModel, MatchController matchController) {
        this.matchViewModel = matchViewModel;
        this.matchController = matchController;
        JPanel buttons = new JPanel();

//      Follow Button
        Follow = new JButton(MatchViewModel.FOLLOW_BUTTON_LABEL);
        buttons.add(Follow);
//      Title
        JLabel title = new JLabel(MatchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//      Cancel Button
        cancel = new JButton(MatchViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        Follow.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
        );
        cancel.addActionListener(this);

        this.add(buttons);
        this.add(title);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}