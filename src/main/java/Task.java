import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Task {
    private final String description;
    private String date;
    private String time;
    /**
     * symbol represents the type of Task.
     * T for Todo.
     * D for Deadline.
     * E for Event.
     */
    private final String symbol;
    private final boolean isDone;

    public Task(String description, String date, String time, String symbol, boolean done) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.symbol = symbol;
        isDone = done;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Changes the status of a task to done.
     * @return The task with the status changed to done.
     */
    public Task markAsDone() {
        return new Task(description, date, time, symbol, true);
    }

    /**
     * Converts the date from DD/MM/YYYY format to D MMM YYYY format.
     */
    public void formatDate() {
        String dateCopy = date;
        StringBuilder year = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder day = new StringBuilder();
        String[] dateArr = dateCopy.split("");
        int slashCount = 0;
        for (String string : dateArr) {
            if (string.equals("/")) {
                slashCount++;
            } else if (slashCount == 2) {
                year.append(string);
            } else if (slashCount == 1) {
                month.append(string);
            } else {
                day.append(string);
            }
        }
        if (day.length() == 1) {
            day.insert(0, "0");
        }
        if (month.length() == 1) {
            month.insert(0, "0");
        }
        if (year.length() == 2) {
            year.insert(0, "20");
        }
        String formattedDate = year + "-" + month + "-" + day;
        LocalDate ld = LocalDate.parse(formattedDate);
        date = ld.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Converts the hours from 24h to 12h format when the tenth digit of the hour is zero.
     * @param onesHour The one digit of the hour.
     * @return The formatted hour in a List.
     */
    public List<String> formatOnesHour(int onesHour) {
        List<String> timeArr = new ArrayList<>();
        if (onesHour == 0) {
            timeArr.add("12:");
            timeArr.add("am");
            return timeArr;
        }
        String hour = String.valueOf(onesHour);
        timeArr.add(hour);
        timeArr.add("am");
        return timeArr;
    }

    /**
     * Converts the hours from 24h to 12h format.
     * @param tensHour The tenth digit of the hour.
     * @param onesHour The one digit of the hour.
     * @return The formatted hour in a List.
     */
    public List<String> formatTensHour(int tensHour, int onesHour) {
        List<String> timeArr = new ArrayList<>();
        int hour = tensHour * 10 + onesHour;
        if (hour >= 13) {
            hour -= 12;
            String hourString = String.valueOf(hour);
            timeArr.add(hourString);
            timeArr.add("pm");
            return timeArr;
        }
        String hourString = String.valueOf(hour);
        timeArr.add(hourString);
        if (hour >= 12) {
            timeArr.add("pm");
            return timeArr;
        }
        timeArr.add("am");
        return timeArr;
    }

    /**
     * Converts the time from 24h to 12h format.
     */
    public String formatTime(String time) {
        char[] timeArr = time.toCharArray();
        int tensHour = timeArr[0] - '0';
        int onesHour = timeArr[1] - '0';
        int tensMin = timeArr[2] - '0';
        int onesMin = timeArr[3] - '0';
        List<String> timeList;
        if (tensHour == 0) {
            timeList = formatOnesHour(onesHour);
        } else {
            timeList = formatTensHour(tensHour, onesHour);
        }
        String min = ":";
        if (tensMin == 0) {
            min += "0";
        } else {
            min += String.valueOf(tensMin);
        }
        min += String.valueOf(onesMin);
        return timeList.get(0) + min + timeList.get(1);
    }

    @Override
    public String toString() {
        String output = "";
        if (symbol.equals("[D]")) {
            output += "(by: " + date + ", " + time + ")";
        }
        if (symbol.equals("[E]")) {
            output += "(at: " + date + ", " + time + ")";
        }
        output = description + output;
        if (isDone) {
            return symbol + " [/] " + output;
        } else {
            return symbol + " [] " + output;
        }
    }
}
