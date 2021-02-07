public class DukeException extends Exception {
    public DukeException(String error){
        super("Uh Oh, there seems to be an error somewhere \n" + error);
    }
}
