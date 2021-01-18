public class DukeException extends Exception {


    public DukeException(String message) {
        super(message);
    }

    public void printMessage(){
        System.out.println("----------------------------------------\n" + super.getMessage()
                + "\n----------------------------------------");
    }

}
