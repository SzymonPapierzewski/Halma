package GUI;

import ProjektHalmav2.App;

import javax.swing.*;
import java.awt.*;

public class PopOutStatement extends JDialog {
    public PopOutStatement(String statement,Point localization){
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.setLocation(localization.x,localization.y + Integer.parseInt(App.config.getProperty("popOutStatementYShift")));
        this.setModal(true);
        this.setSize(Integer.parseInt(App.config.getProperty("popOutStatementWidth")),Integer.parseInt(App.config.getProperty("popOutStatementHeight")));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            panel.add(Box.createVerticalStrut(Integer.parseInt(App.config.getProperty("popOutStatementHeight")) / 6));
            JLabel statementJLabel = new JLabel(statement);
            statementJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(statementJLabel);

            panel.add(Box.createVerticalStrut(20));
            JButton okButton = new JButton("OK");
            okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            okButton.addActionListener(e -> this.dispose());
            panel.add(okButton);
        this.add(panel);

        this.setAutoRequestFocus(true);
        this.setVisible(true);
    }
}
