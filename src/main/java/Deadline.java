import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Deadline extends Task {
    private String endDate;
    public static final char TYPE_SYMBOL = 'D';

    public Deadline(String desc, String endDate) {
        super(desc);
        this.endDate = endDate;
    }

    public static Deadline newInstance(HashMap<String, String> argMap) throws NoSuchElementException {
        if (!argMap.containsKey("desc")) {
            throw new NoSuchElementException("Error: The description for todo cannot be empty.");
        }

        String desc = argMap.get("desc");
        String endDate = argMap.getOrDefault("by", "N/A");
        return new Deadline(desc, endDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + endDate + ")";
    }

    @Override
    public String toSaveFormat() {
        return toSaveFormatPrefix() + saveDelimiter + endDate;
    }

    @Override
    public char typeSymbol() {
        return TYPE_SYMBOL;
    }
}
