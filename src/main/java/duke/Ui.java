package duke;

import duke.command.Command;

public class Ui {
    private String logo =
              " ____        _        \n" //TODO: Figure out if this is allowed by style
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String separator = "------------------\n";

    public void startUpMessage(){
        System.out.println("Hello from\n" + logo);
        System.out.println("No unicode allowed");
    }
    public void goodByeMessage(){
        System.out.println(separator + "Goodbye from\n" + logo);
    }
    public void prompt(){
        System.out.print(separator + "Listening to your input: ");
    }
    public void dumpState(TaskList store){
        System.out.println("Unable to save list. Dumping ...");
        System.out.print(store.getList());
        System.out.println("Continuing Normal operation");
    }
    public void commandMessage(Command command, String data){
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
}
