package Events;

import java.util.ArrayList;
import java.util.List;

public class LanguageChange {
    private List<LanguageChangeListener> listeners = new ArrayList<LanguageChangeListener>();

    public void addLanguageChangeListener(LanguageChangeListener toAdd){
        this.listeners.add(toAdd);
    }

    public void NotifyAllLanguageChangeListeners(){
        for(LanguageChangeListener listener: this.listeners){
            listener.languageChange();
        }
    }
}
