package duke;

import duke.command.*;
import duke.exception.CommandException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    // takes care of parsing the input, then calls Command with appropriate arguements;
    public Parser(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        Command cmd = new HelloCommand();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String command = line.split(" ")[0];
            try {
                switch (command) {
                    case "bye":
                        cmd = new ByeCommand();
                        break;
                    case "list": {
                        cmd = new ListCommand(list);
                        break;
                    }
                    case "done": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("Which task are you done with?");
                        line = line.split(" ",2)[1];
                        int index = Integer.parseInt(line)-1;
                        cmd = new DoneCommand(list,index);
                        break;
                    }
                    case "delete": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("Which task are you deleting?");
                        line = line.split(" ",2)[1];
                        int index = Integer.parseInt(line)-1;
                        cmd = new DeleteCommand(list,index);
                        break;
                    }
                    case "todo": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        cmd = new TodoCommand(list, line);
                        break;
                    }
                    case "deadline": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        String[] result = line.split("/by ");
                        if(result.length==1) throw new CommandException("Er... when do you need to finish this /by?");
                        cmd = new DeadlineCommand(list, result[0], result[1]);
                        break;
                    }
                    case "event": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        String[] result = line.split("/at ");
                        if(result.length==1) throw new CommandException("Er... /at what time does this event start?");
                        cmd = new EventCommand(list, result[0], result[1]);
                        break;
                    }
                    default: {
                        cmd = new ExceptionCommand("I don't understand");
                        break;
                    }
                }

            } catch (IndexOutOfBoundsException | NumberFormatException e){
                cmd = new ExceptionCommand("Please enter a valid value");
            } catch (CommandException e){
                cmd = new ExceptionCommand(e.getMessage());
            }
        }
        sc.close();
    }

}
