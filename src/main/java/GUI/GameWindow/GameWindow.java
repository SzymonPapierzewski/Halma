package GUI.GameWindow;

import Events.BoardStateChangeListener;
import Events.LanguageChangeListener;
import Events.LayoutChangeListener;
import Exceptions.WrongBoardStateTypeException;
import GUI.MyMenuBar;
import GUI.PopOutStatement;
import GUI.StartingWindow.StartingWindow;
import Logic.BoardState;
import ProjektHalmav2.App;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame implements BoardStateChangeListener, LanguageChangeListener, LayoutChangeListener{
    private GameBoard gameBoard;
    private JPanel sidePanel;
    private JLabel playerNameText;
    private JLabel currentPlayerName;
    private JLabel currentPlayerBall;
    private JButton nextPlayerButton;

    public GameWindow(boolean gameFor2Players,String p1Name,int p1Ball,String p2Name, int p2Ball,String p3Name, int p3Ball,String p4Name,int p4Ball){
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        MyMenuBar menu = new MyMenuBar(true);
        menu.getLanguageChanger().addLanguageChangeListener(this);
        menu.getLayoutChanger().addLayoutChangeListener(this);
        this.add(menu,BorderLayout.NORTH);

        this.gameBoard = new GameBoard(gameFor2Players,p1Name,p1Ball,p2Name,p2Ball,p3Name,p3Ball,p4Name,p4Ball);
        this.add(this.gameBoard,BorderLayout.WEST);

        initSidePanel();
        this.add(this.sidePanel,BorderLayout.EAST);

        this.setSize(Integer.parseInt(App.config.getProperty("gameWindowWidth")),Integer.parseInt(App.config.getProperty("gameWindowHeight")));
        this.setVisible(true);

        this.gameBoard.getGame().getBoardHandler().getBoardStateChangeEmiter().addBoardStateChangeListener(this);
    }

    private void initSidePanel(){
        this.sidePanel = new JPanel(new GridBagLayout());
        this.setSize(Integer.parseInt(App.config.getProperty("insidePanelWidth")),Integer.parseInt(App.config.getProperty("insidePanelHeight")));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        this.playerNameText = new JLabel(App.dictionary.getProperty("playerNameText"));
        this.sidePanel.add(this.playerNameText,c);
        c.gridy++;

        this.currentPlayerName = new JLabel(this.gameBoard.getGame().getPlayers()[this.gameBoard.getGame().getCurrentPlayerNumber()].getName());
        this.sidePanel.add(this.currentPlayerName,c);
        c.gridy++;

        this.currentPlayerBall = new JLabel(new ImageIcon(App.options.getProperty("ball" + this.gameBoard.getGame().getPlayers()[this.gameBoard.getGame().getCurrentPlayerNumber()].getBallNumber())));
        this.sidePanel.add(this.currentPlayerBall,c);
        c.gridy++;

        this.nextPlayerButton = new JButton();
        this.nextPlayerButton.setText(App.dictionary.getProperty("pass"));
        this.nextPlayerButton.setEnabled(false);
        this.nextPlayerButton.addActionListener((e) -> nextPlayer());
        this.sidePanel.add(this.nextPlayerButton,c);
    }

    public void nextPlayer(){
        this.gameBoard.nextPlayer();
        this.nextPlayerButton.setEnabled(false);
        this.currentPlayerName.setText(this.gameBoard.getGame().getPlayers()[this.gameBoard.getGame().getCurrentPlayerNumber()].getName());
        this.currentPlayerBall.setIcon(new ImageIcon(App.options.getProperty("ball" + this.gameBoard.getGame().getPlayers()[this.gameBoard.getGame().getCurrentPlayerNumber()].getBallNumber())));
    }

    public void boardStateChange(int boardStateType) throws WrongBoardStateTypeException {
        switch(boardStateType) {
            case BoardState.NOTHING:
                break;
            case BoardState.MARKED:
                break;
            case BoardState.NEXT_PLAYER:
                nextPlayer();
                break;
            case BoardState.MULTI_JUMP:
                this.nextPlayerButton.setEnabled(true);
                break;
            case BoardState.WIN:
                new PopOutStatement(App.dictionary.getProperty("winStatement") + this.currentPlayerName.getText(),this.getLocation());
                this.dispose();
                try {
                    new StartingWindow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //this.gameBoard.win();
                break;
            default:
                throw new WrongBoardStateTypeException();
        }
    }

    @Override
    public void languageChange() {
        this.playerNameText.setText(App.dictionary.getProperty("playerNameText"));
        this.nextPlayerButton.setText(App.dictionary.getProperty("pass"));
    }

    @Override
    public void layoutChange(int layoutNumber) {
        this.gameBoard.layoutChange(layoutNumber);
    }
}
