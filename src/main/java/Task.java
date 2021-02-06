import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private String date;
    private String time;
    private final String symbol;
    private boolean isAt;
    private boolean isDone;

    public Task(String description, String date, String time, String symbol, boolean flag) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.symbol = symbol;
        isAt = flag;
        isDone = false;
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

    /*
     * Change the done status of a task.
     *
     * @return The same task with a changed done status.
     */
    public void markAsDone() {
        isDone = true;
    }

    /*
     * Convert the date from "DD/MM/YYYY" format to "D MMM YYYY" format.
     */
    public void formatDate() {
        String copy = date;
        String year = "";
        String month = "";
        String day = "";
        String[] sArr = copy.split("");
        int slashCounter = 0;
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i].equals("/")) {
                slashCounter++;
            } else if (slashCounter == 2) {
                year += sArr[i];
            } else if (slashCounter == 1) {
                month += sArr[i];
            } else {
                day += sArr[i];
            }
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        String formattedDate = year + "-" + month + "-" + day;
        LocalDate ld = LocalDate.parse(formattedDate);
        formattedDate = ld.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        date = formattedDate;
    }

    /*
     * Convert time from 24h format to 12h format.
     * Separate hour and minute by adding a colon.
     * Add am or pm depending on time of the day.
     */
    public void formatTime() {
        String copy = time;
        char[] cArr = copy.toCharArray();
        Integer tensHour = cArr[0] - '0';
        Integer onesHour = cArr[1] - '0';
        Integer tensMin = cArr[2] - '0';
        Integer onesMin = cArr[3] - '0';
        String formattedTime = "";
        boolean isAfternoon = false;
        if (!tensHour.equals(0)) {
            Integer combinedHour = tensHour * 10 + onesHour;
            if (combinedHour >= 12) {
                isAfternoon = true;
                if (combinedHour >= 13) {
                    combinedHour -= 12;
                }
            }
            formattedTime += combinedHour.toString() + ":";
        } else {
            if (onesHour.equals(0)) {
                formattedTime += "12:";
            } else {
                formattedTime += onesHour.toString() + ":";
            }
        }
        if (tensMin == 0) {
            formattedTime += "0";
        } else {
            formattedTime += tensMin.toString();
        }
        formattedTime += onesMin.toString();
        if (isAfternoon) {
            formattedTime += "pm";
        } else {
            formattedTime += "am";
        }
        time = formattedTime;
    }

    @Override
    public String toString() {
        String copy = "";
        if (date.length() > 0) {
            if (isAt) {
                copy += "(at: ";
            } else {
                copy += "(by: ";
            }
            copy += date;
            if (time.length() > 0) {
                copy += ", " + time;
            }
            copy += ")";
        }
        copy = description + copy;
        if (isDone) {
            return symbol + " " + "[/] " + copy;
        } else {
            return symbol + " " + "[] " + copy;
        }
    }
}
