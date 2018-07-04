package Logic;

public class Player {
    private String name;
    private int ballNumber;

    public Player(String name, int ballNumber){
        this.name = name;
        this.ballNumber = ballNumber;
    }

    public String getName() {
        return name;
    }

    public int getBallNumber() {
        return ballNumber;
    }
}
