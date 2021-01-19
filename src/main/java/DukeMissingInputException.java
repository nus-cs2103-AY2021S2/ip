public class DukeMissingInputException extends Exception {
    public DukeMissingInputException(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return super.getMessage();
    }
}