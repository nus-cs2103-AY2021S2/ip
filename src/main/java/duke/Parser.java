package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Parser {
    public BufferedReader input;
    public UserInterface UI;
    public Storage storage;

    public Parser() throws IOException {
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.UI = new UserInterface();
        this.storage = new Storage("./src/main/data/duke.txt");
    }

    /*
     * Process the user input provided by the UI.
     *
     * @throws IOException
     * @throws InvalidCommandException for unrecognised/invalid commands.
     * @throws ParseException for invalid date input.
     */
    public String process(String command) throws IOException {
        assert !command.equals("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("'by' dd.MM.yyyy HH:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("'from' dd.MM.yyyy HH:mm ");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String[] userInput = command.split(" ", 2);
        if(userInput[0].toLowerCase().equals(Command.TODO.toString())) {
            ToDo newToDo = new ToDo(userInput[1]);
            TaskList.taskList.add(newToDo);
            return UI.addedTask();
        } else if (userInput[0].toLowerCase().equals(Command.HELLO.toString())) {
            return "Hello! Please enter a command.";
        } else if(userInput[0].toLowerCase().equals(Command.DEADLINE.toString())) {
            String[] details = userInput[1].split("/", 2);
            try {
                Date by = dateFormat.parse(details[1]);
                Deadline newDeadline = new Deadline(details[0], by);
                TaskList.taskList.add(newDeadline);
                return UI.addedTask();
            } catch (ParseException e) {
                System.out.println("Please type the date in the format \"by dd.mm.yyyy hh:mm\"!");
            }
        } else if(userInput[0].toLowerCase().equals(Command.EVENT.toString())) {
            String[] details = userInput[1].split("/", 2);
            String[] timings = details[1].split("to ", 2);
            try {
                Date start = dateFormat2.parse(timings[0]);
                Date end = dateFormat3.parse(timings[1]);
                Event newEvent = new Event(details[0], start, end);
                TaskList.taskList.add(newEvent);
                return UI.addedTask();
            } catch (ParseException e){
                System.out.println("Please type the date in the format \"from dd.mm.yyyy hh:mm to dd.mm.yyyy hh:mm\"!");
            }
        } else if(userInput[0].toLowerCase().equals(Command.LIST.toString())){
            return UI.listTasks();
        } else if (userInput[0].toLowerCase().equals(Command.FIND.toString())){
            List<Task> foundTasks = TaskList.find(userInput[1]);
            return UI.listTasks(foundTasks);
        } else if (userInput[0].toLowerCase().equals(Command.REMOVE.toString())){
            String[] details = userInput[1].split(" ", 2);
            if(details[0].toLowerCase().equals(Command.TODO.toString())){
                TaskList.removeTask(new ToDo(details[1]));
                return "Task removed.\n";
            } else if (details[0].toLowerCase().equals(Command.DEADLINE.toString())){
                String description = details[1].split("/", 2)[0];
                TaskList.removeTask(new Deadline(description, new Date()));
                return "Task removed.\n";
            } else if (details[0].toLowerCase().equals(Command.EVENT.toString())){
                String description = details[1].split("/", 2)[0];
                TaskList.removeTask(new Event(description, new Date(), new Date()));
                return "Task removed.\n";
            }
            return "Please enter a valid task.\n";
        } else if(userInput[0].toLowerCase().equals(Command.COMPLETE.toString())) {
            String[] details = userInput[1].split(" ", 2);
            if(details[0].toLowerCase().equals(Command.TODO.toString())){
                TaskList.completeTask(new ToDo(details[1]));
                return "Task marked complete!\n";
            } else if (details[0].toLowerCase().equals(Command.DEADLINE.toString())) {
                String description = details[1].split("/", 2)[0];
                TaskList.completeTask(new Deadline(description, new Date()));
                return "Task marked complete!\n";
            } else if (details[0].toLowerCase().equals(Command.EVENT.toString())) {
                String description = details[1].split("/", 2)[0];
                TaskList.completeTask(new Event(description, new Date(), new Date()));
                return "Task marked complete!\n";
            }
            return "Please enter a valid task.\n";
        } else if (userInput[0].toLowerCase().equals(Command.BYE.toString())) {
            storage.exportTasks();
            System.exit(0);
        } else {
            try {
                throw new InvalidCommandException("Sorry, I don't know what that means!\n" +
                        "Please enter a valid command.\n");
            } catch (InvalidCommandException e) { e.printStackTrace(); }
        }
        return "Please enter a valid command.\n";
    }
}
