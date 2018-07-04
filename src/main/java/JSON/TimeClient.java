package JSON;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class TimeClient {
    private static String TIME_API_URL = "https://script.google.com/macros/s/AKfycbyd5AcbAnWi2Yn0xhFRbyzS4qMq1VucMVgVvhul5XqS9HkAyJY/exec?tz=Poland";
    public static TimeFromGson[] getTime() throws IOException{
        URL url = new URL(TIME_API_URL);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        BufferedReader bufferedReader = new BufferedReader(reader);

        String response = "[" + bufferedReader.lines().collect(Collectors.joining()) + "]";
        Gson g = new Gson();
        return g.fromJson(response,TimeFromGson[].class);
    }
}
