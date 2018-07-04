package Events;

import Exceptions.WrongBoardStateTypeException;

public interface BoardStateChangeListener {
    void boardStateChange(int boardStateType) throws WrongBoardStateTypeException;
}
