import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Event extends Task {

        private LocalDate date;
        private LocalTime time;

        public Event(String details, LocalDate date, LocalTime time) {
            super(details);
            this.date = date;
            this.time = time;
        }

        private Event(String details, LocalDate date, LocalTime time, boolean indicator) {
            super(details, indicator);
            this.date = date;
            this.time = time;
        }

        // overrides completeTask() method
        public Event completeTask() {
            return new Event(this.getTask_details(), date, time,true);
        }

        // overrides taskStatus() method
        public String taskStatus() {
            if (this.isDone()) {
                return "E 1"
                        + this.getTask_details()
                        + " (on: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " "
                        + time.format(DateTimeFormatter.ofPattern("HHmm")) + " )";
            } else {
                return "E 0"
                        + this.getTask_details()
                        + " (on: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " "
                        + time.format(DateTimeFormatter.ofPattern("HHmm")) + " )";
            }
        }
}
