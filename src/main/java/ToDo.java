import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ToDo extends Task {
    public static final char TYPE_SYMBOL = 'T';

    public ToDo(String description) {
        super(description);
    }

    public static ToDo newInstance(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        return new ToDo(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormatPrefix();
    }

    @Override
    public char typeSymbol() {
        return TYPE_SYMBOL;
    }
}
