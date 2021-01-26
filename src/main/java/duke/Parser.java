package duke;

import duke.command.*;
import duke.exception.CommandException;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    // takes care of parsing the input, then calls Command with appropriate arguments;
    public Parser(){
        Scanner sc = new Scanner(System.in);
        new HelloCommand();
        Storage s = new Storage();
        ArrayList<Task> list = new ArrayList<>();
        try{
            list = s.loadData();
        } catch (IOException e){
            e.printStackTrace();
        }
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String command = line.split(" ")[0];
            boolean byeSaid = false;
            try {
                switch (command) {
                    case "bye":
                        new ByeCommand();
                        byeSaid = true;
                        break;
                    case "list": {
                        new ListCommand(list);
                        break;
                    }
                    case "done": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("Which task are you done with?");
                        line = line.split(" ",2)[1];
                        int index = Integer.parseInt(line)-1;
                        new DoneCommand(list,index);
                        break;
                    }
                    case "delete": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("Which task are you deleting?");
                        line = line.split(" ",2)[1];
                        int index = Integer.parseInt(line)-1;
                        new DeleteCommand(list,index);
                        break;
                    }
                    case "todo": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        new TodoCommand(list, line);
                        break;
                    }
                    case "deadline": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        String[] result = line.split("/by ");
                        if(result.length==1) throw new CommandException("Er... when do you need to finish this /by?");
                        try{
                            LocalDate date = LocalDate.parse(result[1]);
                            new DeadlineCommand(list, result[0], date);
                        } catch (DateTimeParseException e){
                            throw new CommandException("Please input a valid date as yyyy-mm-dd");
                        }
                        break;
                    }
                    case "event": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        String[] result = line.split("/at ");
                        if(result.length==1) throw new CommandException("Er... /at what time does this event start?");

                        try{
                            LocalDate date = LocalDate.parse(result[1]);
                            new EventCommand(list, result[0], date);
                        } catch (DateTimeParseException e){
                            throw new CommandException("Please input your date as yyyy-mm-dd");
                        }
                        break;
                    }
                    default: {
                        new ExceptionCommand("I don't understand");
                        break;
                    }
                }

            } catch (IndexOutOfBoundsException | NumberFormatException e){
                new ExceptionCommand("Please enter a valid value");
            } catch (CommandException e){
                new ExceptionCommand(e.getMessage());
            }
            try {
                s.storeData(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(byeSaid) break;
        }
        sc.close();
    }

}
