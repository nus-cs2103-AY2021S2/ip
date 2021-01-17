public class DukeException extends Exception {
    String id;

    public DukeException(String x) {
        id = x;
    }
    public String toString() {
        return "Please enter a proper instruction [" + id + "]";
    }
}
