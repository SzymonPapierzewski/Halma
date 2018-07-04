package Events;

import Exceptions.WrongStartingTypeException;

public interface ChangePanelListener {
    void changePanel(int startingWindowType) throws WrongStartingTypeException;
}
