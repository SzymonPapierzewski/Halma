package Logic;

public class BoardHandlerHighlighter {
    public Square[][] unmarkSquare(Square[][] board, int i, int j) {
        board[i][j].setMarked(false);
        for(Square[] squareRow:board)
            for(Square square : squareRow){
                square.setHighlighted(false);
            }
        return board;
    }

    public Square[][] highlightSquaresFirstJump(Square[][] board, int markedSquareI, int markedSquareJ) {
        for(int i = markedSquareI - 1; i < markedSquareI + 2; i++)
            for(int j = markedSquareJ - 1; j < markedSquareJ + 2; j++){
                if(i < 16 && i > -1 && j < 16 && j > -1){
                    if(board[i][j].getBallNumber() == 0){
                        board[i][j].setHighlighted(true);
                    }else{
                        int hopI = 2*i - markedSquareI;
                        int hopJ = 2*j - markedSquareJ;
                        if(hopI < 16 && hopI > -1 && hopJ < 16 && hopJ > -1){
                            if(board[hopI][hopJ].getBallNumber() == 0){
                                board[hopI][hopJ].setHighlighted(true);
                            }
                        }
                    }
                }
            }
        return board;
    }

    public Square[][] highlightSquaresSecondJump(Square[][] board,int markedSquareI,int markedSquareJ){
        for(int i = markedSquareI - 1; i < markedSquareI + 2; i++)
            for(int j = markedSquareJ - 1; j < markedSquareJ + 2; j++){
                if(i < 16 && i > -1 && j < 16 && j > -1){
                    if(board[i][j].getBallNumber() > 0) {
                        int hopI = 2 * i - markedSquareI;
                        int hopJ = 2 * j - markedSquareJ;
                        if (hopI < 16 && hopI > -1 && hopJ < 16 && hopJ > -1) {
                            if (board[hopI][hopJ].getBallNumber() == 0) {
                                board[hopI][hopJ].setHighlighted(true);
                            }
                        }
                    }
                }
            }
        return board;
    }
}
