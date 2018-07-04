package Events;

import Exceptions.WrongBoardStateTypeException;

import java.util.ArrayList;
import java.util.List;

public class BoardStateChange {

    private List<BoardStateChangeListener> listeners = new ArrayList<BoardStateChangeListener>();

    public void addBoardStateChangeListener(BoardStateChangeListener toAdd){
        this.listeners.add(toAdd);
    }

    public void notifyAllBoardStateChangeListeners(int boardState){
        for(BoardStateChangeListener listener: this.listeners){
            try {
                listener.boardStateChange(boardState);
            } catch (WrongBoardStateTypeException e) {
                e.printStackTrace();
            }
        }
    }
}
