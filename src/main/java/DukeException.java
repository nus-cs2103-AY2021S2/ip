
public class DukeException extends Exception {
    public String errorMessage;
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return this.errorMessage;
    }
}
