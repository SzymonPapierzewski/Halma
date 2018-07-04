package h2;

import java.util.ArrayList;
import java.util.List;

public class WinOutput {
    public static List<String> winners = new ArrayList<>();
    public static List<String> losers1 = new ArrayList<>();
    public static List<String> losers2 = new ArrayList<>();
    public static List<String> losers3 = new ArrayList<>();

    public WinOutput(List<String> winners, List<String> losers1, List<String> losers2, List<String> losers3){
        this.winners = winners;
        this.losers1 = losers1;
        this.losers2 = losers2;
        this.losers3 = losers3;
    }

    public String[][] toArray(){
        String[][] array = new String[this.winners.size()][4];
        for(int i =0; i<this.winners.size();i++) {
            array[i][0] = this.winners.get(i).toString();
            array[i][1] = this.losers1.get(i).toString();
            array[i][2] = this.losers2.get(i).toString();
            array[i][3] = this.losers3.get(i).toString();
        }
        return array;
    }
}
