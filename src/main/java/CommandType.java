
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public enum CommandType {
    ADD_EVENT(){
        @Override
        public boolean isMatching(String input) {
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
        public boolean isMatching(String input) {
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
        public boolean isMatching(String input) {
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
        public boolean isMatching(String input) {
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
        public boolean isMatching(String input) {
            input = input.toLowerCase();
            if (input.startsWith("delete ")||input.equals("delete")) {
                return true;
            } else {
                return false;
            }
        }
    };


    public abstract boolean isMatching(String input);



}
