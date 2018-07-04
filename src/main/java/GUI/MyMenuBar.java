package GUI;

import Events.LanguageChange;
import Events.LayoutChange;
import ProjektHalmav2.App;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;

public class MyMenuBar extends JMenuBar{
    private LanguageChange languageChanger = new LanguageChange();
    private JMenuItem languagePolish;
    private JMenuItem languageEnglish;
    private LayoutChange layoutChanger = new LayoutChange();
    private JMenuItem layout1;
    private JMenuItem layout2;
    private JMenuItem layout3;

    public MyMenuBar(boolean gameWindow){
        JMenu menuLanguage = new JMenu();
        menuLanguage.setText(App.dictionary.getProperty("language"));
        this.add(menuLanguage);

        this.languageEnglish = new JMenuItem(App.dictionary.getProperty("languageEnglish"));
        this.languageEnglish.addActionListener(e -> changeLanguageToEnglish());
        menuLanguage.add(this.languageEnglish);
        this.languagePolish = new JMenuItem(App.dictionary.getProperty("languagePolish"));
        this.languagePolish.addActionListener(e -> changeLanguageToPolish());
        menuLanguage.add(this.languagePolish);

        if(gameWindow) {
            JMenu menuLayout = new JMenu();
            menuLayout.setText(App.dictionary.getProperty("board"));
            this.add(menuLayout);

            this.layout1 = new JMenuItem(App.dictionary.getProperty("layout1"));
            this.layout1.addActionListener(e -> layoutChange(LayoutChange.ONE));
            menuLayout.add(this.layout1);

            this.layout2 = new JMenuItem(App.dictionary.getProperty("layout2"));
            this.layout2.addActionListener(e -> layoutChange(LayoutChange.TOW));
            menuLayout.add(this.layout2);

            this.layout3 = new JMenuItem(App.dictionary.getProperty("layout3"));
            this.layout3.addActionListener(e -> layoutChange(LayoutChange.THREE));
            menuLayout.add(this.layout3);
        }


    }

    private void layoutChange(int layoutNumber) {
        this.layoutChanger.notifyAllLayoutChangeListeners(layoutNumber);
    }

    private void changeLanguageToPolish() {
        try {
            App.dictionary.loadFromXML(new FileInputStream(App.options.getProperty("languagePolish")));
            App.logger.info("Language was changed to Polish");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.languageChanger.NotifyAllLanguageChangeListeners();
    }

    private void changeLanguageToEnglish() {
        try {
            App.dictionary.loadFromXML(new FileInputStream(App.options.getProperty("languageEnglish")));
            App.logger.info("Language was changed to English");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.languageChanger.NotifyAllLanguageChangeListeners();
    }

    public LanguageChange getLanguageChanger() {
        return languageChanger;
    }

    public LayoutChange getLayoutChanger() {
        return layoutChanger;
    }
}
