public class DukeException extends Exception {
    public DukeException(String errorMessage, Throwable cause) {
        //super(errorMessage, cause);
        System.out.println("---------------------------------------------");
        System.out.println(errorMessage);
        System.out.println("---------------------------------------------");
    }
}