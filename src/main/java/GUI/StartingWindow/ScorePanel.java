package GUI.StartingWindow;

import Events.ChangePanel;
import JSON.TimeClient;
import JSON.TimeFromGson;
import ProjektHalmav2.App;
import h2.GameResult;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class ScorePanel extends JPanel {
    private ChangePanel changePanel;
    private JButton backButton;
    private JLabel date;

    public ScorePanel(ChangePanel changePanel) throws IOException {
        this.changePanel = changePanel;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        TimeFromGson[] time = TimeClient.getTime();
        this.date = new JLabel(time[0].toString());
        this.add(this.date,c);
        c.gridy++;

        String[] columnNames = {"Winner","Loser","Loser","Loser"};

//        new GameResult().getScoreList(list -> {
//            String[][] data = list.toArray();
//            JTable table = new JTable(data, columnNames);
//            JScrollPane scrollPane = new JScrollPane(table);
//            this.add(scrollPane, BorderLayout.CENTER);
//        });

        this.backButton = new JButton(App.dictionary.getProperty("backButton"));
        this.backButton.addActionListener(e -> back());
        this.add(this.backButton,c);
    }

    private void back() {
        this.changePanel.notifyAllChangePanelListeners(StartingWindowType.STARTIN_PANEL);
    }

    public void languageChange() {
        this.backButton.setText(App.dictionary.getProperty("backButton"));
    }
}
