package duke;

public class DukeException extends Exception{
    protected String message;

    public DukeException(String message){
        this.message =message;
    }

    public String toString(){
        return ":< OOPS!!! " + message;
    }
}
