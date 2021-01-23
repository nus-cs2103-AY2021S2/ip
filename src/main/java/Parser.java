import java.time.LocalDate;

public class Parser {

    /**
     * Takes in user's command line arguments and treats them accordingly.
     * Chatbot Duke will end the session when user inputs "bye".
     */
    public static void handleUserCommand(String input, Ui ui) throws DukeException {
            if (input.equals("bye")) {
                ui.showGoodbyeMessage();
                Duke.sayBye = true;
            } else if (input.equals("list")) {
                ui.showTaskList();
            } else if (input.startsWith("done ")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                Task task = TaskList.taskList.get(taskNumber - 1);
                TaskList.markTaskDone(task);
                ui.showTaskDone(task);
            } else if (input.startsWith("todo ")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String todoTask = getTodoTask(input);
                TaskList.createTodoTask(todoTask);
                ui.showTaskAdded();
                ui.showNumberOfTasks();
            } else if (input.startsWith("event ")) {
                String eventTask = getEventTask(input);
                String date = getEventDate(input);
                TaskList.createEventTask(eventTask, date);
                ui.showTaskAdded();
                ui.showNumberOfTasks();
            } else if (input.startsWith("deadline ")) {
                String deadlineTask = getDeadlineTask(input);
                LocalDate deadline = getDeadlineDate(input);
                TaskList.createDeadlineTask(deadlineTask, deadline);
                ui.showTaskAdded();
                ui.showNumberOfTasks();
            } else if (input.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(input.substring(7));
                Task taskDeleted = TaskList.taskList.get(taskNumber-1);
                TaskList.deleteTask(taskNumber);
                ui.showTaskDeleted(taskDeleted);
                ui.showTaskAdded();
                ui.showNumberOfTasks();
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
    }

    public static String getTodoTask(String input) {
        return input.substring(5);
    }

    public static String getEventTask(String input) {
        return input.substring(6).split("/")[0];
    }

    public static String getEventDate(String input) {
        return input.substring(6).split("/")[1].substring(3);
    }

    public static String getDeadlineTask(String input) {
        return input.substring(9).split("/",2)[0];
    }

    public static LocalDate getDeadlineDate(String input) {
        return LocalDate.parse(input.substring(9).split("/",2)[1].substring(3)
                .replaceAll("/","-"));
    }



}
