package Duke.Exception;
public class InvalidIndex extends IndexOutOfBoundsException{
    String type;
    int listSize;

    public InvalidIndex(String type, int listSize) {
        this.type = type;
        this.listSize = listSize;
    }

    @Override
    public String getMessage() {
        return type + " command should be followed by a number between 1 and " + listSize + ".";
    }
}
