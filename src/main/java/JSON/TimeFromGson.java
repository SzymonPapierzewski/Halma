package JSON;

public class TimeFromGson {
    private String dayofweek;
    private String dayofweekName;
    private String day;
    private String month;
    private String monthName;
    private String year;
    private String hours;
    private String minutes;
    private String seconds;
    private String millis;
    private String fulldate;
    private String timezone;
    private String status;

    @Override
    public String toString(){
        return year + "." + month + "." + day + " - " + hours + ":" + minutes;
    }
}