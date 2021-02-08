package duke;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;

import java.text.ParseException;

public class Ui {
    private String logo =
              " ____        _        \n" //TODO: Figure out if this is allowed by style
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String separator = "------------------\n";

    public void startUpMessage() {
        System.out.println("Hello from\n" + logo);
        System.out.println("No unicode allowed");
    }
    public void goodByeMessage(){
        System.out.println(separator + "Goodbye from\n" + logo);
    }
    public void prompt(){
        System.out.print(separator + "Listening to your input: ");
    }
    public void loadStart() {
        System.out.println("Loading From File...");
    }
    public void loadSuccess() {
        System.out.println("Loaded");
    }
    public void loadFail() {
        System.out.println("Failed to Load file. Aborting.");
    }
    public void dumpState(TaskList store) {
        System.out.println("Unable to save list. Dumping ...");
        System.out.print(store.getList());
        System.out.println("Continuing Normal operation");
    }
    public void commandMessage(Command command, String data) {
        switch(command.getType()){
        case LIST:
            System.out.print(data);
            break;
        case DONE:
            System.out.println("The following task is now marked as done:\n" +
                    data);
            break;
        case ADD:
            System.out.println("The following task has been added:\n" +
                    data);
            break;
        case DELETE:
            System.out.println("The following Task has been deleted:");
            System.out.println(data);
            break;
        }
    }
    //TODO: Figure out if this overloading is acceptable from a coding style perspective.
    public void handleException(ParseException e) {
        System.out.println("Command has invalid parsing.");
        System.out.println(e.getMessage());
    }
    public void handleException(InvalidCommandException e){
        System.out.println(e.getMessage());
    }
    public void handleException(EmptyArgumentException e){
        System.out.println("Cannot have empty argument");
        System.out.println(e.getMessage());
    }
    public void handleException(BadDateArgumentException e) {
        System.out.println("Date must be of format 'dd MM yyyy'; Eg: 27 08 2044");
        System.out.println(e.getMessage());
    }
}
