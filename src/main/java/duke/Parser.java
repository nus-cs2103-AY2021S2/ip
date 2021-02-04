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
     * @throws InvalidCommandException for unrecognised commands.
     * @throws ParseException for invalid date input.
     */
    public void process() throws IOException {
        this.UI.prompt();
        SimpleDateFormat dateFormat = new SimpleDateFormat("'by' dd.MM.yyyy HH:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("'from' dd.MM.yyyy HH:mm ");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String[] userInput = input.readLine().split(" ", 2);
        if(userInput[0].toLowerCase().equals(Command.TODO.toString())){
            ToDo newToDo = new ToDo(userInput[1]);
            TaskList.taskList.add(newToDo);
            UI.addedTask();
        } else if(userInput[0].toLowerCase().equals(Command.DEADLINE.toString())) {
            String[] details = userInput[1].split("/", 2);
            try {
                Date by = dateFormat.parse(details[1]);
                Deadline newDeadline = new Deadline(details[0], by);
                TaskList.taskList.add(newDeadline);
                UI.addedTask();
            } catch (ParseException e) {
                System.out.println("Please type the date in the format \"by dd.mm.yyyy hh:mm\"!");
            }
        } else if(userInput[0].toLowerCase().equals(Command.EVENT.toString())){
            String[] details = userInput[1].split("/", 2);
            String[] timings = details[1].split("to ", 2);
            try {
                Date start = dateFormat2.parse(timings[0]);
                Date end = dateFormat3.parse(timings[1]);
                Event newEvent = new Event(details[0], start, end);
                TaskList.taskList.add(newEvent);
                UI.addedTask();
            } catch (ParseException e){
                System.out.println("Please type the date in the format \"from dd.mm.yyyy hh:mm to dd.mm.yyyy hh:mm\"!");
            }
        } else if(userInput[0].toLowerCase().equals(Command.LIST.toString())) {
            UI.listTasks();
        } else if (userInput[0].toLowerCase().equals(Command.FIND.toString())) {
            List<Task> foundTasks = TaskList.find(userInput[1]);
            UI.output.write("There were " + foundTasks.size() + " tasks containing your keyword.\n");
            UI.listTasks(foundTasks);
            if (foundTasks.size() > 0) {
                UI.foundPrompt();
                int response = Integer.parseInt(input.readLine());
                try {
                    if (response > 2 || response < 1) {
                        throw new InvalidCommandException("Please enter [1] or [2].");
                    } else if (response == 1) {
                        UI.output.write("Which task would you like to mark as complete? Please select the number.\n");
                        UI.output.flush();
                        int taskNumber = Integer.parseInt(input.readLine());
                        if (taskNumber > foundTasks.size()) {
                            throw new InvalidCommandException("Please enter a valid task number.");
                        } else {
                            Task toComplete = foundTasks.get(taskNumber - 1);
                            TaskList.completeTask(toComplete);
                            UI.output.write("Would you like to remove this task?\n" +
                                    "[1] Yes\n" +
                                    "[2] No\n");
                            UI.output.flush();
                            int response2 = Integer.parseInt(input.readLine());
                            if (response2 > 2 || response2 < 1) {
                                throw new InvalidCommandException("Please enter [1] or [2].\n");
                            } else if (response2 == 1) {
                                TaskList.removeTask(toComplete);
                            }
                        }
                    } else {
                        UI.output.write("Which task would you like to remove? Please select the number.\n");
                        UI.output.flush();
                        int taskNumber = Integer.parseInt(input.readLine());
                        if (taskNumber > foundTasks.size()) {
                            throw new InvalidCommandException("Please enter a valid task number.\n");
                        } else {
                            Task toRemove = foundTasks.get(taskNumber - 1);
                            TaskList.removeTask(toRemove);
                        }
                    }
                } catch (InvalidCommandException e) { e.printStackTrace(); }
            }
        } else if (userInput[0].toLowerCase().equals(Command.COMPLETE.toString())) {
            try {
                List<Task> foundTasks = TaskList.find(userInput[1]);
                UI.listTasks(foundTasks);
                UI.output.write("Which task would you like to mark as complete? Please select the number.\n");
                UI.output.flush();
                int taskNumber = Integer.parseInt(input.readLine());
                if (taskNumber > foundTasks.size()) {
                    throw new InvalidCommandException("Please enter a valid task number.\n");
                } else {
                    Task toComplete = foundTasks.get(taskNumber - 1);
                    TaskList.completeTask(toComplete);
                    UI.output.write("Would you like to remove this task?\n" +
                            "[1] Yes\n" +
                            "[2] No\n");
                    UI.output.flush();
                    int response2 = Integer.parseInt(input.readLine());
                    if (response2 > 2 || response2 < 1) {
                        throw new InvalidCommandException("Please enter [1] or [2].\n");
                    } else if (response2 == 1) {
                        TaskList.removeTask(toComplete);
                    }
                }
            } catch (InvalidCommandException e) { e.printStackTrace(); }
        } else if(userInput[0].toLowerCase().equals(Command.REMOVE.toString())) {
            try {
                List<Task> foundTasks = TaskList.find(userInput[1]);
                UI.listTasks(foundTasks);
                UI.output.write("Which task would you like to mark as complete? Please select the number.\n");
                UI.output.flush();
                int taskNumber = Integer.parseInt(input.readLine());
                if (taskNumber > foundTasks.size()) {
                    throw new InvalidCommandException("Please enter a valid task number.");
                } else {
                    Task toRemove = foundTasks.get(taskNumber - 1);
                    TaskList.removeTask(toRemove);
                }
            } catch (InvalidCommandException e) { e.printStackTrace(); }
        } else if (userInput[0].toLowerCase().equals(Command.BYE.toString())){
            UI.goodBye();
            storage.exportTasks();
            System.exit(0);
        } else {
            try {
                throw new InvalidCommandException("Sorry, I don't know what that means!\n" +
                        "Please enter a valid command.\n");
            } catch (InvalidCommandException e) { e.printStackTrace(); }
        }
    }
}
