import java.util.HashMap;
import java.util.NoSuchElementException;

public class ToDo extends Task {
    public static final String COMMAND_STRING = "todo";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static ToDo newInstance(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        return new ToDo(desc, argMap.containsKey("done"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected HashMap<String, String> saveArgs() {
        HashMap<String, String> argMap = new HashMap<>();
        return argMap;
    }

    @Override
    public String commandString() {
        return COMMAND_STRING;
    }
}
