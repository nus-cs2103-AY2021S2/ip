public class Event extends Task {

        private String date;

        public Event(String details, String date) {
            super(details);
            this.date = date;
        }

        private Event(String details, String date, boolean indicator) {
            super(details, indicator);
            this.date = date;
        }

        // overrides completeTask() method
        public Event completeTask() {
            return new Event(this.getTask_details(), date,true);
        }

        // overrides taskStatus() method
        public String taskStatus() {
            if (this.isDone()) {
                return "E 1 " + this.getTask_details() + " (at: " + date + ")";
            } else {
                return "E 0 " + this.getTask_details() + " (at: " + date + ")";
            }
        }
}
