package Logic;

import Events.BoardStateChange;
import Events.WinEvent;
import Exceptions.WronBoradStateException;
import ProjektHalmav2.App;

public class BoardHandler {
    private int[][] checkingWinTable;
    private int markedI;
    private int markedJ;
    private BoardHandlerHighlighter highlighter = new BoardHandlerHighlighter();
    private BoardHandlerJumper jumper = new BoardHandlerJumper();
    private BoardStateChange boardStateChangeEmiter = new BoardStateChange();

    public BoardHandler(boolean gameFor2Players){
        this.checkingWinTable = new int[16][16];
        this.checkingWinTable = (gameFor2Players ? BoardState.twoPlayersWin : BoardState.fourPlayersWin);
        this.markedI = 0;
        this.markedJ = 0;
    }

    public Square[][] squareClicked(Square[][] board, int i, int j, Player currentPlayer) throws WronBoradStateException {
        if(Game.boardState == BoardState.NEXT_PLAYER) Game.boardState = BoardState.NOTHING;
        switch(Game.boardState) {
            case BoardState.NOTHING:
                board = mark(board,i,j,currentPlayer);
                App.logger.info("Player: " + currentPlayer.getName() + " marked field [" + i + "," + j + "]");
                break;
            case BoardState.MARKED:
                if(board[i][j].isMarked()) {
                    board = ummark(board,i,j,currentPlayer);
                }
                if(board[i][j].isHighlighted()){
                    if(Math.abs(this.markedI - i) < 2 && Math.abs(this.markedJ - j) < 2 ){
                        board = move(board, i, j, currentPlayer);
                    }else{
                        board = jump(board, i, j, currentPlayer);
                    }
                }
                App.logger.info("Player: " + currentPlayer.getName() + " moved his ball from field [" + this.markedI + "," + this.markedJ + "] to field ["+ i + "," + j + "]");
                break;
            case BoardState.MULTI_JUMP:
                if(board[i][j].isHighlighted()){
                    jump(board, i, j, currentPlayer);
                }
                break;
            default:
                throw new WronBoradStateException();
        }

        if(isWin(board,currentPlayer)){
            App.logger.info("Player: " + currentPlayer.getName() + " won the game.");
            new WinEvent();
            Game.boardState = BoardState.WIN;
        }
        this.boardStateChangeEmiter.notifyAllBoardStateChangeListeners(Game.boardState);
        return board;
    }

    private boolean isWin(Square[][] board, Player currentPlayer) {
        boolean win = true;
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++){
                if(board[i][j].getBallNumber() == currentPlayer.getBallNumber() && board[i][j].getBallNumber() != this.checkingWinTable[i][j]){
                    win = false;
                }
            }
        return win;
    }


    private Square[][] mark(Square[][] board, int i, int j, Player currentPlayer){
        if(board[i][j].getBallNumber() == currentPlayer.getBallNumber()) {
            this.markedI = i;
            this.markedJ = j;
            board[i][j].setMarked(true);
            board = this.highlighter.highlightSquaresFirstJump(board, i, j);
            Game.boardState = BoardState.MARKED;
        }
        return board;
    }

    private Square[][] ummark(Square[][] board, int i, int j, Player currentPlayer){
        board[i][j].setMarked(false);
        board = this.highlighter.unmarkSquare(board, i, j);
        Game.boardState = BoardState.NOTHING;
        return board;
    }

    private Square[][] move(Square[][] board, int i, int j, Player currentPlayer){
        board = this.jumper.jump(this.markedI,this.markedJ,i,j,board);
        board = this.highlighter.unmarkSquare(board,this.markedI,this.markedJ);
        Game.boardState = BoardState.NEXT_PLAYER;
        return board;
    }

    private Square[][] jump(Square[][] board, int i, int j, Player currentPlayer){
        board = this.jumper.jump(this.markedI,this.markedJ,i,j,board);
        board = this.highlighter.unmarkSquare(board,this.markedI,this.markedJ);
        board = markMultiJump(board,i,j,currentPlayer);
        Game.boardState = BoardState.MULTI_JUMP;
        return board;
    }

    private Square[][] markMultiJump(Square[][] board, int i, int j, Player currentPlayer){
        if(board[i][j].getBallNumber() == currentPlayer.getBallNumber()) {
            this.markedI = i;
            this.markedJ = j;
            board[i][j].setMarked(true);
            board = this.highlighter.highlightSquaresSecondJump(board, i, j);
        }
        return board;
    }

    public void clearMarksAndHighlights(Square[][] board){
        for(Square[] squaerRow: board)
            for(Square square: squaerRow){
            square.setMarked(false);
            square.setHighlighted(false);
            }
    }

    public BoardStateChange getBoardStateChangeEmiter() {
        return boardStateChangeEmiter;
    }
}















//        if(BoardState.boardState == BoardState.MARKED){
//                if(board[i][j].isMarked()) {
//                board = highlighter.unmarkSquare(board, i, j);
//                BoardState.boardState = BoardState.NOTHING;
//                }
//                if(board[i][j].isHighlighted()) {
//                board = jumper.jump(this.markedI,this.markedJ,i,j,board);
//                if( Math.abs(this.markedI - i) < 2 && Math.abs(this.markedJ - j) < 2 ){
//        board = highlighter.unmarkSquare(board,this.markedI,this.markedJ);
//        BoardState.boardState = BoardState.NOTHING;
//        this.jumper.getBoardStateChangeEmiter().notifyAllBoardStateChangeListeners(BoardState.NEXT_PLAYER);
//        }else{
//        BoardState.boardState = BoardState.MULTI_JUMP;
//        this.markedI = i;
//        this.markedJ = j;
//        this.highlighter.highlightSquaresSecendJump(board,this.markedI,this.markedJ);
//
//
//        }
//
//
//        }
//        }else{
//            /* Zaznaczenie kulki */
//        if(board[i][j].getBallNumber() == currentPlayer.getBallNumber()) {
//        board[i][j].setMarked(true);
//        board = highlighter.highlightSquaresFirstJump(board, i, j);
//        this.sthIsMarked = true;
//        this.markedI = i;
//        this.markedJ = j;
//        }
//        }