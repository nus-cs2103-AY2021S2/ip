package duke;

import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        String separator = "------------------\n";
        ui.startUpMessage();
        List<Task> store = null;
        try {
            System.out.println("Loading From File...");
            store = TaskListFileUtils.LoadTaskList();
            System.out.println("Loaded");
        } catch (IOException e) {
            System.out.println("Failed to Load file. Aborting.");
            return;
        }
        Scanner in = new Scanner(System.in);
        String line;
        String tokens[] = {"invalid"};
        do{
            System.out.print(separator + "Listening to your input: ");
            line = in.nextLine();
            int hash = store.hashCode(); //To detect changes later.
            try {
                String singleTokens[] = {"bye","list"};
                tokens = splitTokenIntoTwo(line," ", singleTokens);
                switch(tokens[0]){
                    case "bye":
                        ui.goodByeMessage();                        break;
                    case "list":
                        printList(store);
                        break;
                    case "done":
                        int index = Integer.valueOf(tokens[1]) - 1;
                        Task t = store.get(index);
                        t.isDone = true;
                        System.out.println("The following task is now marked as done:\n" +
                                formatOrderedPrint(store,index));
                        break;
                    case "todo":
                        store.add(new ToDos(tokens[1]));
                        System.out.println("The following todo item has been added:\n" +
                                formatOrderedPrint(store,-1));
                        break;
                    case "deadline":
                        tokens = splitTokenIntoTwo(tokens[1]," /by ");
                        store.add(new Deadline(tokens[0],tokens[1]));
                        System.out.println("The following deadline item has been added:\n" +
                                formatOrderedPrint(store,-1));
                        break;
                    case "event":
                        tokens = splitTokenIntoTwo(tokens[1]," /at ");
                        store.add(new Event(tokens[0],tokens[1]));
                        System.out.println("The following event item has been added:\n" +
                                formatOrderedPrint(store,-1));
                        break;
                    case "delete":
                        int deleteIndex = Integer.valueOf(tokens[1]) - 1;
                        System.out.println("Deleting the following duke.task.Task:");
                        System.out.println(formatOrderedPrint(store, deleteIndex));
                        store.remove(deleteIndex);
                        System.out.println("Done");
                        break;
                    default:
                        throw new InvalidCommandException(tokens[0]);
                }
            } catch (ParseException e) {
                System.out.println("Command.Command has invalid parsing.");
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e){
                System.out.println(e.getMessage());
            } catch(EmptyArgumentException e){
                System.out.println("Cannot have empty argument");
            } catch (BadDateArgumentException e) {
                System.out.println("Date must be of format 'dd MM yyyy'; Eg: 27 08 2044");
            } finally {
                int newHash = store.hashCode();
                if(newHash != hash){
                    try {
                        TaskListFileUtils.saveTaskList(store);
                    } catch (IOException e) {
                        System.out.println("Unable to save list. Dumping ...");
                        printList(store);
                        System.out.println("Continuing Normal operation");
                    }
                }
            }
        }while(!tokens[0].equals("bye"));
        in.close();
    }

    private static void printList(List<Task> store){
        for (int i = 0 ; i < store.size(); i++) {
            System.out.println(formatOrderedPrint(store,i));
        }
    }

    private static String formatOrderedPrint(List<Task> tasks, int i){
        final int size = tasks.size();
        while (i < 0){
            i += size;
        }
        while (i >= size){
            i -= size;
        }
        return "Entry " + (i+1) + "|" + tasks.get(i).toString();
    }
    private static String[] splitTokenIntoTwo(String parseTarget,String delimiter) throws ParseException{
        String[] tokens = parseTarget.split(delimiter,2);
        if (tokens.length < 2){
            throw new ParseException("Expected deliminter '"+ delimiter +"'", tokens[0].length());
        }
        return tokens;
    }
    
    private static String[] splitTokenIntoTwo(String parseTarget,String delimiter, String[] exception) throws ParseException{
        List<String> exceptionList = Arrays.asList(exception);
        String[] tokens = parseTarget.split(delimiter,2);
        if (!exceptionList.contains(tokens[0]) && tokens.length < 2){
            throw new ParseException("Expected deliminter '"+ delimiter +"'", tokens[0].length());
        }
        return tokens;
    }
}
