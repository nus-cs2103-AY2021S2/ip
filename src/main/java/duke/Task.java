package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private String identity;
    private String name;
    private boolean isCompleted;
    private String date;

    protected Task(String identity, String name) {
        this.identity = identity;
        this.isCompleted = false;
        if (name.contains("/")) {
            String[] nameArr = name.split("/...");
            this.name = nameArr[0];
            this.date = formatDate(nameArr[1]);
        } else {
            this.name = name;
            this.date = " ";
        }
    }

    protected Task(String identity, String[] strArr) {
        this.identity = identity;
        this.isCompleted = Integer.parseInt(strArr[1]) == 1 ? true : false;
        this.name = strArr[2];
        this.date = strArr[3];
    }

    protected String formatDate(String newDate) {
        // Looks for inputs with the format yyyy-mm-dd also works with / or . as the
        // separator.
        String regex = "(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])";
        Matcher m = Pattern.compile(regex).matcher(newDate);
        if (m.find()) {
            LocalDate tempDate = LocalDate.parse(m.group(0));
            String dateFormat = tempDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return newDate.replaceAll(regex, dateFormat);
        } else {
            return newDate;
        }
    }

    public String toString() {
        if (!isCompleted()) {
            return String.format("[%s][ ] %s", this.getIdentity(), this.getName());
        } else {
            return String.format("[%s][X] %s", this.getIdentity(), this.getName());
        }
    }

    public String storeString() {
        return String.format("%s | %d | %s | %s", this.getIdentity(), this.isCompleted() ? 1 : 0, this.getName(),
                this.getDate());
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getName() {
        return name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
