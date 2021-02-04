package duke;

import duke.exceptions.*;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


public class Duke {
    public static final String DIRECTORY = System.getProperty("user.dir");
    public static TaskList tasks;
    private Storage storage;
    private final Ui ui;

    /**
     * Constructor for Duke object
     */

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DIRECTORY);
        this.tasks = new TaskList();
        storage.loadTasks(tasks);
    }

    public void run() {
        String input;
        ui.start();

        while (ui.canRead()) {
            input = ui.readInput();

            while (!input.equals("bye")) {
                try {
                    String[] inputWords = input.split(" ");
                    String action = inputWords[0];

                    if (input.equals("list")) {
                        if (tasks.getSize() == 0) {
                            throw new EmptyListException("List is empty.");
                        }
                        ui.getList(tasks);
                        input = ui.readInput();
                    } else if (action.equals("done")) {
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments.");
                        } else if (tasks.getSize() == 0) {
                            throw new EmptyListException("List is empty.");
                        } else if (Integer.parseInt(inputWords[1]) > tasks.getSize()) {
                            throw new NotExistingTaskException("No such task number");
                        }
                        int index = Integer.parseInt(inputWords[1]);

                        Task completedTask = tasks.getTask(index - 1);
                        completedTask.markCompleted();
                        ui.taskDone(completedTask);

                        input = ui.readInput();
                    } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")){
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        }
                        StringBuilder sb = new StringBuilder();
                        String taskItem;

                        if (action.equals("todo")) {
                            for (int i = 1; i < inputWords.length; i++) {
                                sb.append(" ");
                                sb.append(inputWords[i]);
                            }
                            taskItem = sb.toString();
                            ToDo toDoItem = new ToDo(taskItem);
                            tasks.addTask(toDoItem);

                            ui.addTask(toDoItem);
                        } else {
                            int slashIndex = 0;

                            for (int i = 0; i < inputWords.length; i++) {
                                if (inputWords[i].contains(Character.toString('/'))) {
                                    slashIndex = i;
                                }
                            }
                            for (int j = 1; j < slashIndex; j++) {
                                sb.append(" ");
                                sb.append(inputWords[j]);
                            }
                            taskItem = sb.toString();
                            StringBuilder sbSlash = new StringBuilder();

                            for (int k = slashIndex + 1; k < inputWords.length; k++) {
                                sbSlash.append(" ");
                                sbSlash.append(inputWords[k]);
                            }
                            try {
                                if (action.equals("deadline")) {
                                    Deadline deadlineItem = new Deadline(taskItem, sbSlash.toString());
                                    tasks.addTask(deadlineItem);
                                    ui.addTask(deadlineItem);
                                } else {
                                    Event eventItem = new Event(taskItem, sbSlash.toString());
                                    tasks.addTask(eventItem);
                                    ui.addTask(eventItem);
                                }
                            } catch (Exception e) {
                                throw new DateTimeFormatException("Wrong datetime format given.");
                            }
                        }
                        ui.getTaskListSize(tasks);
                        input = ui.readInput();
                    } else if (action.equals("delete")) {
                        if (inputWords.length < 2) {
                            throw new MissingArgumentException("Wrong number of arguments");
                        } else if (tasks.getSize() == 0) {
                            throw new EmptyListException("List is empty.");
                        } else if (Integer.parseInt(inputWords[1]) > tasks.getSize()) {
                            throw new NotExistingTaskException("No such task number");
                        }
                        int deleteIndex = Integer.parseInt(inputWords[1]);

                        ui.deleteTask(tasks.getTask(deleteIndex - 1));
                        tasks.removeTask(deleteIndex - 1);
                        ui.getTaskListSize(tasks);

                        input = ui.readInput();
                    } else {
                        throw new NoKeywordException("No such action.");
                    }
                } catch (MissingArgumentException error) {
                    System.out.println(
                            "\nMaster, I'm afraid you're missing the task number.");
                    input = ui.readInput();
                } catch (NoKeywordException error) {
                    System.out.println(
                            "\nSorry Master but I cannot do that.");
                    input = ui.readInput();
                } catch (DateTimeFormatException error) {
                    System.out.println(
                            "\nMaster, please input the date and time as such: \"YYYY-MM-DD HH:MM\"");
                    input = ui.readInput();
                } catch (EmptyListException error) {
                    System.out.println(
                            "\nThe list is empty, Master.");
                    input = ui.readInput();
                } catch (NotExistingTaskException error) {
                    System.out.println(
                            "\nI'm afraid the task number is not in the list, Master.");
                    input = ui.readInput();
                }
            }
            ui.goodBye();
            storage.saveTaskList(tasks);
            break;
        }
        System.exit(1);
    }
}
