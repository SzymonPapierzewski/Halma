package GUI.GameWindow;

import Exceptions.WronBoradStateException;
import Exceptions.WrongLayoutNumber;
import Logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel{
    private Game game;

    private SquareJLabel[][] squares;

    public GameBoard(boolean gameFor2Players,String p1Name,int p1Ball,String p2Name, int p2Ball,String p3Name, int p3Ball,String p4Name,int p4Ball){
        this.game = new Game(gameFor2Players,p1Name,p1Ball,p2Name,p2Ball,p3Name,p3Ball,p4Name,p4Ball);
        this.squares = new SquareJLabel[16][16];

        this.setLayout(new GridLayout(16,16));
        for(int i=0 ; i<this.squares.length ; i++)
            for (int j=0 ; j<this.squares[i].length ; j++){
                this.squares[i][j] = new SquareJLabel(i,j,this.game.getBoard()[i][j].getBallNumber(),this.game.getBoard()[i][j].isHighlighted());
                this.squares[i][j].addMouseListener(new SquareListener(i,j) {
                    public void mouseClicked(MouseEvent e) {
                        try {
                            boardClicked(this.getI(),this.getJ());
                        } catch (WronBoradStateException e1) {
                            e1.printStackTrace();
                        }
                    }

                    public void mousePressed(MouseEvent e) {

                    }

                    public void mouseReleased(MouseEvent e) {

                    }

                    public void mouseEntered(MouseEvent e) {

                    }

                    public void mouseExited(MouseEvent e) {

                    }
                });
                this.add(this.squares[i][j]);
            }

        this.setSize(16*squares[0][0].getWidth(),16*squares[0][0].getHeight());
    }

    void boardClicked(int i, int j) throws WronBoradStateException {
        this.game.boardClicked(i,j);
        for(int ii=0 ; ii<this.squares.length ; ii++)
            for (int jj=0 ; jj<this.squares[i].length ; jj++){
            this.squares[ii][jj].repaintSquare(this.game.getBoard()[ii][jj].getBallNumber(),this.game.getBoard()[ii][jj].isHighlighted());
            }
    }

    public void nextPlayer() {
        this.game.nextPlayer();
        for(int i = 0; i < this.squares.length; i++)
            for(int j = 0; j < this.squares.length; j++){
            this.squares[i][j].repaintSquare(this.game.getBoard()[i][j].getBallNumber(),this.game.getBoard()[i][j].isHighlighted());
            }
    }

    public Game getGame() {
        return game;
    }

    public void win() {
        this.game.win();
    }

    public void layoutChange(int layoutNumber) {
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++){
                try {
                    this.squares[i][j].layoutChange(layoutNumber);
                } catch (WrongLayoutNumber wrongLayoutNumber) {
                    wrongLayoutNumber.printStackTrace();
                }
            }
    }
}
