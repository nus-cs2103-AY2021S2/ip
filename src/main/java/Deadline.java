import java.util.NoSuchElementException;
import java.util.Optional;

public class Deadline extends Task {
    private String endDate;
    public static final char TYPE_SYMBOL = 'D';

    public Deadline(String desc, String endDate) {
        super(desc);
        this.endDate = endDate;
    }

    public static Deadline newInstance(String args) {
        String[] argArr = args.split("/");
        for (String s: argArr) {
            if (s.startsWith("by ")) {
                return new Deadline(argArr[0], s.substring(3));
            }
        }
        return new Deadline(argArr[0], "N/A");
    }

    public static Deadline newInstance(Optional<String> argsOpt) throws NoSuchElementException{
        String[] argArr;

        try {
            argArr = argsOpt.get().split("/");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: The description for deadline cannot be empty");
        }

        for (String s: argArr) {
            if (s.startsWith("by ")) {
                return new Deadline(argArr[0], s.substring(3));
            }
        }
        return new Deadline(argArr[0], "N/A");
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
