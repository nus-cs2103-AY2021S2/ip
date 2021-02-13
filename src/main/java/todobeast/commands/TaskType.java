package todobeast.commands;

/**
 * Enumeration that defines the types of tasks that can be created within the application.
 */
public enum TaskType {
    TODO {
        @Override
        public String toString() {
            return "TODO";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "DEADLINE";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "EVENT";
        }
    }
}
