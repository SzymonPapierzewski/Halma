package Events;

import GUI.PopOutStatement;

import java.awt.*;

public class WinEvent{
    public WinEvent(){
        for (int i = 0; i < 10; i++)
            new PopOutStatement("!!!!!!!",new Point(i * 10,i * 10));
        }
}
