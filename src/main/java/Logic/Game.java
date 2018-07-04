package Logic;

import Exceptions.DBTaskUndoneException;
import Exceptions.WronBoradStateException;
import h2.GameResult;

public class Game{
    private boolean gameFor2Players;
    private Square[][] board;
    private Player[] players;
    private int currentPlayerNumber;
    private BoardHandler boardHandler;
    public static int boardState;

    public Game(boolean gameFor2Players,String p1Name,int p1Ball,String p2Name, int p2Ball,String p3Name, int p3Ball,String p4Name,int p4Ball){
        this.gameFor2Players = gameFor2Players;
        boardState = BoardState.NOTHING;
        players = (gameFor2Players ? new Player[2] : new Player[4]);
        if(gameFor2Players){
            this.players[0] = new Player(p1Name,p1Ball);
            this.players[1] = new Player(p2Name,p2Ball);
        }else{
            this.players[0] = new Player(p1Name,p1Ball);
            this.players[1] = new Player(p2Name,p2Ball);
            this.players[2] = new Player(p3Name,p3Ball);
            this.players[3] = new Player(p4Name,p4Ball);
        }

        this.currentPlayerNumber = 0;
        this.boardHandler = new BoardHandler(gameFor2Players);
        this.board = new Square[16][16];

        int[][] initBoard = (gameFor2Players ? BoardState.initTable2Players/*BoardState.testWin*/ : BoardState.initTable4Players);
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++){
            if(initBoard[i][j] == 0){
                this.board[i][j] = new Square(i,j,0);
            }else{
                this.board[i][j] = new Square(i,j,this.players[initBoard[i][j] - 1].getBallNumber());
            }
            }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void boardClicked(int i, int j) throws WronBoradStateException {
        this.board = this.boardHandler.squareClicked(this.board,i,j,this.players[this.currentPlayerNumber]);
    }

    public void nextPlayer() {
        this.currentPlayerNumber = (this.currentPlayerNumber + 1) % this.players.length;
        boardHandler.clearMarksAndHighlights(this.board);
        Game.boardState = BoardState.NEXT_PLAYER;
    }

    public BoardHandler getBoardHandler() {
        return boardHandler;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void win() {
        if(this.gameFor2Players){
            try {
                new GameResult().saveResult(this.players[this.currentPlayerNumber].getName(),this.players[(this.currentPlayerNumber + 1)%this.players.length].getName(),"-","-");
            } catch (DBTaskUndoneException e) {
                e.printStackTrace();
            }
        }else{
            try {
                new GameResult().saveResult(this.players[this.currentPlayerNumber].getName(),this.players[(this.currentPlayerNumber + 1)%this.players.length].getName(),this.players[(this.currentPlayerNumber + 1)%this.players.length].getName(),this.players[(this.currentPlayerNumber + 1)%this.players.length].getName());
            } catch (DBTaskUndoneException e) {
                e.printStackTrace();
            }
        }
    }
}
