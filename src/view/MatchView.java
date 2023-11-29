package view;

import interface_adapter.match.MatchController;
import interface_adapter.match.MatchViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MatchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Matches";

    private final MatchViewModel matchViewModel;
    private final MatchController matchController;

    private final JButton Follow;


    public MatchView(MatchViewModel matchViewModel, MatchController matchController, JButton follow) {
        this.matchViewModel = matchViewModel;
        this.matchController = matchController;
        JPanel buttons = new JPanel();
        Follow = new JButton(MatchViewModel.FOLLOW_BUTTON_LABEL);
        buttons.add(Follow);

        Follow.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    }
                }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}