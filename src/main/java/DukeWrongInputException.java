public class DukeWrongInputException extends Exception{
    public DukeWrongInputException(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return super.getMessage();
    }
}