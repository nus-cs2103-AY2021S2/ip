import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeProcessor {
    private final String fullTimeString;

    public DateTimeProcessor(String fullTimeString) {
        this.fullTimeString = fullTimeString;
    }

    private String processTime(String time){
        try{
            if (time.isEmpty()) return "";
            else if (time.length() != 3 && time.length() != 4) return null;
            int timeInt = Integer.parseInt(time);
            int hour = timeInt / 100;
            int minute = timeInt % 100;
            String type;
            if (hour >= 12) type = "pm";
            else type = "am";
            hour = hour % 12;
            if (hour == 0) hour = 12;
            String padding = (minute < 10) ? "0" : "";
            return hour + ":" + padding + minute + type;
        } catch (NumberFormatException e){
            return null;
        }
    }

    private String processDate(String date){
        try{
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e){
            return null;
        }
    }

    private String[] processInput(){
        try {
            String date = "";
            String time = "";
            String[] info = fullTimeString.trim().split(" ");
            for (String s : info){
                if (date.isEmpty()) date = s;
                else {
                    time = s;
                    break;
                }
            }
            return new String[]{date, time};
        } catch (Exception e){
            return null;
        }
    }

    public String getFullDateTime(){
        String[] array = processInput();
        if (array == null) return "Invalid format for date and time.";
        String date = processDate(array[0]);
        String time = processTime(array[1]);
        if (date == null || time == null) return "Invalid format for date and time.";
        return (time.isEmpty()) ? date : date + " " + time;
    }
}
