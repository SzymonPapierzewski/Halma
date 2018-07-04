package GUI.StartingWindow;

import Events.ChangePanel;
import GUI.PopOutStatement;
import ProjektHalmav2.App;

import javax.swing.*;
import java.awt.*;

public class StartingPanel extends JPanel{
    private ChangePanel changePanel;
    private JButton newGameButton;
    private JButton scoreButton;
    private JButton exitButton;

    public StartingPanel(ChangePanel changePanel){
        this.changePanel = changePanel;
        this.setSize(Integer.parseInt(App.config.getProperty("startingWindowWidth")),Integer.parseInt(App.config.getProperty("startingWindowHeight")));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        this.add(Box.createVerticalStrut(Integer.parseInt(App.config.getProperty("startingWindowHeight")) / 3));

        this.newGameButton = new JButton(App.dictionary.getProperty("newGameButton"));
        this.newGameButton.addActionListener(e -> changePanelFunction(StartingWindowType.NEW_GAME_PANEL));
        this.newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this.newGameButton);

        this.add(Box.createVerticalStrut(10));

        this.scoreButton = new JButton(App.dictionary.getProperty("scoreButton"));
        this.scoreButton.addActionListener(e -> changePanelFunction(StartingWindowType.SCORE_PANEL));
        this.scoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this.scoreButton);

        this.add(Box.createVerticalStrut(10));

        this.exitButton = new JButton(App.dictionary.getProperty("exitButton"));
        this.exitButton.addActionListener(e -> exit());
        this.exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(this.exitButton);

    }

    private void changePanelFunction(int startingWindowType){
        this.changePanel.notifyAllChangePanelListeners(startingWindowType);
    }

    private void exit(){
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }

    public void languageChange() {
        this.newGameButton.setText(App.dictionary.getProperty("newGameButton"));
        this.scoreButton.setText(App.dictionary.getProperty("scoreButton"));
        this.exitButton.setText(App.dictionary.getProperty("exitButton"));
    }
}
