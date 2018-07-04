package Logic;

public class BoardHandlerJumper {

    public Square[][] jump(int markedI, int markedJ, int i, int j, Square[][] board) {
        board[i][j].setBallNumber(board[markedI][markedJ].getBallNumber());
        board[markedI][markedJ].setBallNumber(0);

        return board;
    }
}
