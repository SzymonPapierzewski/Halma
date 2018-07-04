package GUI.GameWindow;

import Events.LayoutChange;
import Exceptions.WrongLayoutNumber;
import ProjektHalmav2.App;

import javax.swing.*;

public class SquareJLabel extends JLabel{
    private int i;
    private int j;

    private JLabel ballLabel;
    private JLabel highlightedLabel;

    public SquareJLabel(int i, int j,int ballNumber, boolean highlighted){
        this.i = i;
        this.j = j;
        this.ballLabel = new JLabel();
        this.highlightedLabel = new JLabel();
        this.add(this.ballLabel);
        this.add(this.highlightedLabel);

        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        if(i%2 == 0 && j%2 == 0 || i%2 == 1 && j%2 == 1){
            this.setIcon(new ImageIcon(App.options.getProperty("blackSquare1")));
        }else{
            this.setIcon(new ImageIcon(App.options.getProperty("whiteSquare1")));
        }
        this.setSize(Integer.parseInt(App.config.getProperty("squareWidth")),Integer.parseInt(App.config.getProperty("squareHeight")));
        repaintSquare(ballNumber,highlighted);
    }

    public void repaintSquare(int ballNumber, boolean highlighted){
        if(ballNumber > 0){
            this.ballLabel.setVisible(true);
            this.ballLabel.setIcon(new ImageIcon(App.options.getProperty("ball" + ballNumber)));
        }else{
            this.ballLabel.setVisible(false);
        }
        this.highlightedLabel.setIcon(new ImageIcon((highlighted ? App.options.getProperty("highlight") : App.options.getProperty("blank"))));
    }

    public void layoutChange(int layoutNumber) throws WrongLayoutNumber {
        String blackSquare, whiteSquare;
        blackSquare = "blackSquare1";
        whiteSquare = "whiteSquare1";
        switch(layoutNumber){
            case LayoutChange.ONE:
                break;
            case LayoutChange.TOW:
                blackSquare = "blackSquare2";
                whiteSquare = "whiteSquare2";
                break;
            case LayoutChange.THREE:
                blackSquare = "blackSquare3";
                whiteSquare = "whiteSquare3";
                break;
            default:
                throw new WrongLayoutNumber();
        }
        if(i%2 == 0 && j%2 == 0 || i%2 == 1 && j%2 == 1){
            this.setIcon(new ImageIcon(App.options.getProperty(blackSquare)));
        }else{
            this.setIcon(new ImageIcon(App.options.getProperty(whiteSquare)));
        }
    }

}