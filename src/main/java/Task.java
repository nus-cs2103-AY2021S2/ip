import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    private String description;
    private final String date;
    private final String time;
    private boolean isAt;
    private boolean isDone;

    public Task(String description, String date, String time, boolean flag) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.isAt = flag;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String dateFormatter(String date) {
        String year = "";
        String month = "";
        String day = "";
        String[] sArr = date.split("");
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
        return ld.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public String timeFormatter(String time) {
        char[] cArr = time.toCharArray();
        Integer tensHour = cArr[0] - '0';
        Integer onesHour = cArr[1] - '0';
        Integer tensMin = cArr[2] - '0';
        Integer onesMin = cArr[3] - '0';
        String output = "";
        boolean isAfternoon = false;
        if (!tensHour.equals(0)) {
            Integer combinedHour = tensHour * 10 + onesHour;
            if (combinedHour >= 12) {
                isAfternoon = true;
                if (combinedHour >= 13) {
                    combinedHour -= 12;
                }
            }
            output += combinedHour.toString() + ":";
        } else {
            if (onesHour.equals(0)) {
                output += "12:";
            } else {
                output += onesHour.toString() + ":";
            }
        }
        if (tensMin == 0) {
            output += "0";
        } else {
            output += tensMin.toString();
        }
        output += onesMin.toString();
        if (isAfternoon) {
            output += "pm";
        } else {
            output += "am";
        }
        return output;
    }

    @Override
    public String toString() {
        String copy = "";
        if (this.date.length() > 0) {
            if (this.isAt) {
                copy += "(at: ";
            } else {
                copy += "(by: ";
            }
            copy += this.dateFormatter(this.date);
            if (this.time.length() > 0) {
                copy += ", " + this.timeFormatter(this.time);
            }
            copy += ")";
        }
        copy = this.description + copy;
        if (this.isDone) {
            return "[X] " + copy;
        } else {
            return "[ ] " + copy;
        }
    }
}
