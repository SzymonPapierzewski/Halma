package Events;

import java.util.ArrayList;
import java.util.List;

public class LayoutChange {
    public static final int ONE = 0;
    public static final int TOW = 1;
    public static final int THREE = 2;

    private List<LayoutChangeListener> listeners = new ArrayList<>();

    public void addLayoutChangeListener(LayoutChangeListener toAdd){
        this.listeners.add(toAdd);
    }

    public void notifyAllLayoutChangeListeners(int layoutNumber){
        for(LayoutChangeListener listener: this.listeners){
            listener.layoutChange(layoutNumber);
        }
    }
}
