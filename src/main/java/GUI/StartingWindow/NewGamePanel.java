package GUI.StartingWindow;

import Events.ChangePanel;
import GUI.GameWindow.GameWindow;
import GUI.PopOutStatement;
import ProjektHalmav2.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewGamePanel extends JPanel implements ActionListener{
    private ChangePanel changePanel;
    private JRadioButton twoPlayersChoice;
    private JRadioButton fourPlayersChoice;
    private JPanel twoPlayersPanel;
    private JPanel fourPlayersPanel;
    private List<JLabel> chooseYourBallText = new ArrayList<>();
    private List<JLabel> playerNameText = new ArrayList<>();
    private List<JComboBox> coloursFor2PlayersGame = new ArrayList<>();
    private List<JComboBox> coloursFor4PlayersGame = new ArrayList<>();
    private List<JTextField> namesFor2PlayersGame = new ArrayList<>();
    private List<JTextField> namesFor4PlayersGame = new ArrayList<>();
    private JButton backButton;
    private JButton playButton;

    private String[] ballColours = {App.dictionary.getProperty("red"),App.dictionary.getProperty("blue"),App.dictionary.getProperty("green"),App.dictionary.getProperty("yellow")};

    public NewGamePanel(ChangePanel changePanel) {
        this.changePanel = changePanel;
        this.setSize(Integer.parseInt(App.config.getProperty("startingWindowWidth")),Integer.parseInt(App.config.getProperty("startingWindowHeight")));
        this.setLayout(new BorderLayout());

        JPanel jRadioButtonsPanel = new JPanel();
            this.twoPlayersChoice = new JRadioButton(App.dictionary.getProperty("twoPlayersJRadioButton"));
            this.twoPlayersChoice.setSelected(true);
            this.fourPlayersChoice = new JRadioButton(App.dictionary.getProperty("fourPlayersJRadioButton"));
            jRadioButtonsPanel.add(this.twoPlayersChoice);
            jRadioButtonsPanel.add(this.fourPlayersChoice);
            ButtonGroup group = new ButtonGroup();
            group.add(this.twoPlayersChoice);
            group.add(this.fourPlayersChoice);
            this.twoPlayersChoice.addActionListener(this);
            this.fourPlayersChoice.addActionListener(this);
        this.add(jRadioButtonsPanel,BorderLayout.NORTH);


        JPanel templatePanel = new JPanel(new CardLayout());
            this.twoPlayersPanel = new JPanel();
            this.twoPlayersPanel.setLayout(new GridLayout(8,1));
            addRowsToPlayersPanel(2,this.twoPlayersPanel);
            templatePanel.add(this.twoPlayersPanel);

            this.fourPlayersPanel = new JPanel();
            this.fourPlayersPanel.setLayout(new GridLayout(16,1));
            addRowsToPlayersPanel(4,this.fourPlayersPanel);
            templatePanel.add(this.fourPlayersPanel);
        this.add(templatePanel,BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout());
            this.backButton = new JButton(App.dictionary.getProperty("backButton"));
            this.backButton.addActionListener(e -> back());
            bottomPanel.add(this.backButton);

            this.playButton = new JButton(App.dictionary.getProperty("playButton"));
            this.playButton.addActionListener(e -> newGame());
            bottomPanel.add(this.playButton);
        this.add(bottomPanel,BorderLayout.SOUTH);


    }

    private void back() {
        this.changePanel.notifyAllChangePanelListeners(StartingWindowType.STARTIN_PANEL);
    }

    private void newGame() {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            int p1ball, p2ball, p3ball, p4ball;
            String p1Name, p2Name, p3Name, p4Name;
            if (this.twoPlayersChoice.isSelected()) {
                p1Name = this.namesFor2PlayersGame.get(0).getText();
                p2Name = this.namesFor2PlayersGame.get(1).getText();
                p1ball = this.coloursFor2PlayersGame.get(0).getSelectedIndex() + 1;
                p2ball = this.coloursFor2PlayersGame.get(1).getSelectedIndex() + 1;
                if(p1ball == p2ball){
                    PopOutStatement wrongBallStatement = new PopOutStatement(App.dictionary.getProperty("wrongBallStatement"),topFrame.getLocation());
                }else if(p1Name.equals("") || p2Name.equals("") || p1Name.equals(p2Name)){
                    PopOutStatement wrongPlayerNameStatement = new PopOutStatement(App.dictionary.getProperty("wrongPlayerNameStatement"),topFrame.getLocation());
                }else {
                    topFrame.dispose();
                    GameWindow gw = new GameWindow(true, p1Name, p1ball, p2Name, p2ball, "q", 3, "q", 4);
                }
            } else {
                p1Name = this.namesFor4PlayersGame.get(0).getText();
                p2Name = this.namesFor4PlayersGame.get(1).getText();
                p3Name = this.namesFor4PlayersGame.get(2).getText();
                p4Name = this.namesFor4PlayersGame.get(3).getText();
                p1ball = this.coloursFor4PlayersGame.get(0).getSelectedIndex() + 1;
                p2ball = this.coloursFor4PlayersGame.get(1).getSelectedIndex() + 1;
                p3ball = this.coloursFor4PlayersGame.get(2).getSelectedIndex() + 1;
                p4ball = this.coloursFor4PlayersGame.get(3).getSelectedIndex() + 1;
                Set<Integer> setOfBalls = new HashSet<>();
                setOfBalls.add(p1ball);
                setOfBalls.add(p2ball);
                setOfBalls.add(p3ball);
                setOfBalls.add(p4ball);
                Set<String> setOfPlayerNames = new HashSet<>();
                setOfPlayerNames.add(p1Name);
                setOfPlayerNames.add(p2Name);
                setOfPlayerNames.add(p3Name);
                setOfPlayerNames.add(p4Name);

                if(setOfBalls.size() != 4){
                    PopOutStatement wrongBallStatement = new PopOutStatement(App.dictionary.getProperty("wrongBallStatement"),topFrame.getLocation());
                }else if(setOfPlayerNames.size() != 4){
                    PopOutStatement wrongPlayerNameStatement = new PopOutStatement(App.dictionary.getProperty("wrongPlayerNameStatement"),topFrame.getLocation());
                }else {
                    topFrame.dispose();
                    GameWindow gw = new GameWindow(false, p1Name, p1ball, p2Name, p2ball, p3Name, p3ball, p4Name, p4ball);
                }
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(twoPlayersChoice.isSelected()){
            this.twoPlayersPanel.setVisible(true);
            this.fourPlayersPanel.setVisible(false);
        }else{
            this.twoPlayersPanel.setVisible(false);
            this.fourPlayersPanel.setVisible(true);
        }
    }

    private void addRowsToPlayersPanel(int howMany, JPanel witchPanel){
        for(int i = 0; i < howMany;i++) {
            JLabel playerJLabel = new JLabel(App.dictionary.getProperty("playerNameText"));
            witchPanel.add(playerJLabel);
            JTextField textField = new JTextField();
            witchPanel.add(textField);
            JLabel chooseYourBallJLabel = new JLabel((App.dictionary.getProperty("chooseYourBallText")));
            this.playerNameText.add(playerJLabel);
            this.chooseYourBallText.add(chooseYourBallJLabel);
            witchPanel.add(chooseYourBallJLabel);
            JComboBox colourList = new JComboBox(this.ballColours);
            if(howMany == 2) {
                this.coloursFor2PlayersGame.add(colourList);
                this.namesFor2PlayersGame.add(textField);
            }else{
                this.coloursFor4PlayersGame.add(colourList);
                this.namesFor4PlayersGame.add(textField);
            }
            colourList.setSelectedIndex(i);
            witchPanel.add(colourList);

        }
    }

    public void languageChange() {
        this.ballColours = new String[]{App.dictionary.getProperty("red"), App.dictionary.getProperty("blue"), App.dictionary.getProperty("green"), App.dictionary.getProperty("yellow")};
        this.twoPlayersChoice.setText(App.dictionary.getProperty("twoPlayersJRadioButton"));
        this.fourPlayersChoice.setText(App.dictionary.getProperty("fourPlayersJRadioButton"));
        for(JLabel playerName: this.playerNameText){
            playerName.setText(App.dictionary.getProperty("playerNameText"));
        }
        for(JLabel chooseYourBall: this.chooseYourBallText){
            chooseYourBall.setText(App.dictionary.getProperty("chooseYourBallText"));
        }
        for(JComboBox colorComboBox: this.coloursFor2PlayersGame){
            DefaultComboBoxModel model = new DefaultComboBoxModel( this.ballColours );
            colorComboBox.setModel(model);
        }
        for(JComboBox colorComboBox: this.coloursFor4PlayersGame){
            DefaultComboBoxModel model = new DefaultComboBoxModel( this.ballColours );
            colorComboBox.setModel(model);
        }
        this.backButton.setText(App.dictionary.getProperty("backButton"));
        this.playButton.setText(App.dictionary.getProperty("playButton"));
    }
}
