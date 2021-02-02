public class Event extends Task {
        public Event(String description) {
            super(description);
        }

        public Event(String description, boolean b) {
            super(description,b);
        }

        public String toString() {
            return "[E]" + super.toString();
        }
}

