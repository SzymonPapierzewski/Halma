package GUI.StartingWindow;

import Events.ChangePanel;
import Events.ChangePanelListener;
import Events.LanguageChangeListener;
import Exceptions.WrongStartingTypeException;
import GUI.MyMenuBar;
import ProjektHalmav2.App;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartingWindow extends JFrame implements ChangePanelListener, LanguageChangeListener {
    private ChangePanel changePanel = new ChangePanel();
    private StartingPanel startingPanel;
    private NewGamePanel newGamePanel;
    private ScorePanel scorePanel;
    private JPanel panel;


    public StartingWindow() throws IOException {
        this.changePanel.addChangePanelListener(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(App.config.getProperty("startingWindowTitle"));
        this.setSize(Integer.parseInt(App.config.getProperty("startingWindowWidth")),Integer.parseInt(App.config.getProperty("startingWindowHeight")));
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        MyMenuBar qwe = new MyMenuBar(false);
        qwe.getLanguageChanger().addLanguageChangeListener(this);
        this.add(qwe,BorderLayout.NORTH);



        this.panel = new JPanel();
        this.panel.setLayout(new CardLayout());

        this.startingPanel = new StartingPanel(this.changePanel);
        this.newGamePanel = new NewGamePanel(this.changePanel);
        this.scorePanel = new ScorePanel(this.changePanel);
        this.panel.add(this.startingPanel);
        this.panel.add(this.newGamePanel);
        this.panel.add(this.scorePanel);
        this.add(this.panel,BorderLayout.CENTER);

        JLabel bg = new JLabel(new ImageIcon("src/main/resources/images/background1.jpg"));
        bg.setBounds(0,0,Integer.parseInt(App.config.getProperty("startingWindowWidth")),Integer.parseInt(App.config.getProperty("startingWindowHeight")));
        this.panel.add(bg);

        try {
            changePanel(StartingWindowType.STARTIN_PANEL);
        } catch (WrongStartingTypeException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
    }


    @Override
    public void changePanel(int startingWindowType) throws WrongStartingTypeException {
        switch (startingWindowType){
            case StartingWindowType.STARTIN_PANEL:
                this.startingPanel.setVisible(true);
                this.newGamePanel.setVisible(false);
                this.scorePanel.setVisible(false);
                this.setTitle(App.dictionary.getProperty("startingPanelTitle"));
                break;
            case StartingWindowType.NEW_GAME_PANEL:
                this.startingPanel.setVisible(false);
                this.newGamePanel.setVisible(true);
                this.scorePanel.setVisible(false);
                this.setTitle(App.dictionary.getProperty("newGamePanelTitle"));
                break;
            case StartingWindowType.OPTIONS_PANEL:
                this.startingPanel.setVisible(false);
                this.newGamePanel.setVisible(false);
                this.scorePanel.setVisible(false);
                this.setTitle(App.dictionary.getProperty("optionsPanelTitle"));
                break;
            case StartingWindowType.SCORE_PANEL:
                this.startingPanel.setVisible(false);
                this.newGamePanel.setVisible(false);
                this.scorePanel.setVisible(true);
                this.setTitle(App.dictionary.getProperty("scorePanelTitle"));
                break;
            default:
                throw new WrongStartingTypeException();
        }
    }

    @Override
    public void languageChange() {
        this.startingPanel.languageChange();
        this.newGamePanel.languageChange();
        this.scorePanel.languageChange();
    }
}
