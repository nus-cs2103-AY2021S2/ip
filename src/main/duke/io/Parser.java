package main.duke.io;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.duke.command.*;
import main.duke.DukeException;

public class Parser {
    /**
     *
     * @param input the whole user console input command
     * @return Command for execution
     * @throws DukeException thrown when an invalid message is inputted
     */
    public ICommand parse(String input) throws DukeException{
        String[] splitString = input.split(" ");
        assert (splitString.length > 0);
        String first = splitString[0];
        ICommand iCommand;
        switch (first){
            case"bye":
                checkLength(1,splitString);
                iCommand = new CBye();
                break;
            case"done":
                checkLength(2,splitString);
                iCommand = new CDone(input);
                break;
            case"delete":
                checkLength(2,splitString);
                iCommand = new CDelete(input);
                break;
            case"deadline":
                checkMessage(first,input);
                checkDateTime(splitString);
                checkBreakExist("/by", input, "deadline");
                iCommand = new CDeadline(input);
                break;
            case"find":
                checkMessage(first, input);
                iCommand = new CFind(input);
                break;
            case"event":
                checkMessage(first, input);
                checkDateTime(splitString);
                checkBreakExist("/at", input, "event");
                iCommand = new CEvent(input);
                break;
            case"list":
                checkLength(1, splitString);
                iCommand = new CList();
                break;
            case"todo":
                checkMessage(first, input);
                iCommand = new CTodo(input);
                break;
            case"help":
                checkLength(1,splitString);
                iCommand = new CHelp();
                break;
            default:
                throw new DukeException("The request is not valid");
        }
        return iCommand;
    }
    public void checkLength(int length, String[] input) throws DukeException{
        if(length != input.length){
            throw new DukeException("Your message is not the right length");
        }
    }
    public void checkMessage(String command, String wholeInput) throws DukeException{
        if(command.length() + 1 >= wholeInput.length()){
            throw new DukeException(command + " cannot be empty!");
        }
    }
    public void checkBreakExist(String type, String wholeInput, String command) throws DukeException{
        if(!wholeInput.contains(type)){
            throw new DukeException(command + " is not complete. You are missing " + type);
        }
    }
    public void checkDateTime(String[] command) throws DukeException {
        String dateTime = command[command.length - 2] + " " + command[command.length -1];
        try{
            LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        }
        catch (DateTimeParseException e){
            throw new DukeException("You have the wrong date time format. I require YYYY-MM-DD 0000");
        }
    }
}
