import java.io.IOException;

public class Parser {
    private static final String SAVED_FORMAT_DELIMITER = " \\| ";
    private final TaskList taskList;

    Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse input from file and return the corresponding task
     * @param taskString
     * @return Task
     */

    public static Task parseFileInput(String taskString) {
        String[] split = taskString.split(SAVED_FORMAT_DELIMITER);
        String type = split[0];
        String isDone = split[1];
        switch (type) {
            case "T":
                String descriptionToDo = split[2];
                ToDo taskT = new ToDo(descriptionToDo);
                if (isDone.equals("X")) {
                    taskT.markAsDone();
                }
                return taskT;
            case "D":
                String descriptionDeadlines = split[2];
                String byDeadlines = split[3];
                Deadlines taskD = new Deadlines(descriptionDeadlines, byDeadlines, true);
                if (isDone.equals("X")) {
                    taskD.markAsDone();
                }
                return taskD;
            case "E":
                String descriptionEvents = split[2];
                String byEvents = split[3];
                Deadlines taskE = new Deadlines(descriptionEvents, byEvents, true);
                if (isDone.equals("X")) {
                    taskE.markAsDone();
                }
                return taskE;
            default:
                return null;

        }

    }

    /**
     * Parse input from user and add the task to taskList
     * @param commandFromUser
     */
    public void parseUserCommand(String commandFromUser) throws IOException, DukeException {
        if (commandFromUser.equals("list")) {
            this.taskList.listTask();
        }
        else if (commandFromUser.split(" ")[0].equals("done")) {
            int index = Integer.parseInt(commandFromUser.split(" ")[1]);
            this.taskList.finishTask(index);
        }
        else if (commandFromUser.split(" ")[0].equals("delete")) {
            int index = Integer.parseInt(commandFromUser.split(" ")[1]);
            this.taskList.deleteTask(index);
        }
        else if (commandFromUser.split(" ")[0].equals("todo")) {
            if (commandFromUser.split(" ").length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String res = commandFromUser.split("todo ")[1];
            this.taskList.addTask(new ToDo(res));
        }
        else if (commandFromUser.split(" ")[0].equals("deadline")) {
            String[] res = (commandFromUser.split("deadline ")[1]).split(" /by ");
            if (res.length != 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = res[0];
            String by = res[1];
            Task task = new Deadlines(description, by, false);
            this.taskList.addTask(task);
        }

        else if (commandFromUser.split(" ")[0].equals("event")) {
            String[] res = (commandFromUser.split("event ")[1]).split(" /at ");
            String description = res[0];
            String by = res[1];
            this.taskList.addTask(new Events(description, by));
        }

        else if (commandFromUser.split(" ")[0].equals("find")) {
            String keyword = commandFromUser.split(" ")[1];
            this.taskList.find(keyword);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
