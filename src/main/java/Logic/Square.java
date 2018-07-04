package Logic;

public class Square {
    private int i;
    private int j;
    private int ballNumber;
    private boolean highlighted;
    private boolean marked;

    public Square(int i, int j, int ballNumber){
        this.i = i;
        this.j = j;
        this.ballNumber = ballNumber;
        this.highlighted = false;
        this.marked = false;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
