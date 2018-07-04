package Events;

import Exceptions.WrongStartingTypeException;

import java.util.ArrayList;
import java.util.List;

public class ChangePanel {
    private List<ChangePanelListener> listeners = new ArrayList<ChangePanelListener>();

    public void addChangePanelListener(ChangePanelListener toAdd){
        this.listeners.add(toAdd);
    }

    public void notifyAllChangePanelListeners(int startingWindowType){
        for(ChangePanelListener listener:this.listeners){
            try {
                listener.changePanel(startingWindowType);
            } catch (WrongStartingTypeException e) {
                e.printStackTrace();
            }
        }
    }
}
