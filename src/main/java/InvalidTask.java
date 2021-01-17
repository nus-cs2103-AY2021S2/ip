public class InvalidTask extends Exception{
    String type;

    public InvalidTask(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "Invalid command for " + type + "! Try again";
    }
}
