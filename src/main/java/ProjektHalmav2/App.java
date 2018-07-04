package ProjektHalmav2;

import GUI.StartingWindow.StartingWindow;
import JSON.TimeClient;
import JSON.TimeFromGson;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import h2.GameResult;
import org.apache.log4j.Logger;


public class App
{
    public static Properties dictionary;
    public static Properties config;
    public static Properties options;
    public static final Logger logger = Logger.getLogger(App.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main( String[] args ) throws IOException {
        logger.info("Halma - Start");
        config = new Properties();
        config.load(new FileInputStream("src/main/resources/config.properties"));
        dictionary = new Properties();
        dictionary.loadFromXML(new FileInputStream("src/main/resources/dictionary_pl.xml"));
        options = new Properties();
        options.load(new FileInputStream("src/main/resources/options.properties"));

        StartingWindow sw = new StartingWindow();

        executorService.shutdown();
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }

}
