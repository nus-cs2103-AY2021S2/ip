public class DukeException extends Exception{
    protected String message;
    DukeException(String message){
        this.message =message;
    }

    public String toString(){
        return ":< OOPS!!! " + message;
    }
}
