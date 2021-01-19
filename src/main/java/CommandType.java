
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An enum type which defines the type of command issue to the chatbot,
 * A command will order the chatbot to do something.
 * So far there are five commands -> to add an event at some time, to add a deadline by some time,
 * to add something to do, to delete a task and to mark a task as done.
 */

public enum CommandType {
    ADD_EVENT(){
        @Override
        public boolean isCommandTypeFor(String input) {
            input = input.toLowerCase();
            if (input.startsWith("event ") || input.equals("event")){
                return true;
            } else {
                return false;
            }
        }
    },
    ADD_DEADLINE() {
        @Override
        public boolean isCommandTypeFor(String input) {
            input = input.toLowerCase();
            if (input.startsWith("deadline ") || input.equals("deadline")) {
                return true;
            } else {
                return false;
            }
        }
    },
    ADD_TODO() {
        @Override
        public boolean isCommandTypeFor(String input) {
            input = input.toLowerCase();
            if (input.startsWith("todo ")||input.equals("todo")) {
                return true;
            } else {
                return false;
            }
        }
    },
    MARK_AS_DONE() {
        @Override
        public boolean isCommandTypeFor(String input) {
            input = input.toLowerCase();
            if (input.startsWith("done ")|| input.equals("done")) {
                return true;
            } else {
                return false;
            }
        }
    },
    REMOVE_TASK() {
        @Override
        public boolean isCommandTypeFor(String input) {
            input = input.toLowerCase();
            if (input.startsWith("delete ")||input.equals("delete")) {
                return true;
            } else {
                return false;
            }
        }
    };

    /**
     * Checks if the string belongs to the command_type of the calling enum class.
     * @param input to be analysed if it is of the command_type.
     * @return whether the input is of the commandtype
     */
    public abstract boolean isCommandTypeFor(String input);



}
