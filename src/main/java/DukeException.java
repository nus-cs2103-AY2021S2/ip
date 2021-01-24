public class DukeException extends Exception {

    public DukeException(String e) {
        super("Oops! " + e);
    }
//    public String toString() {
//        return "Please enter a proper instruction [" + id + "]";
//    }
}
