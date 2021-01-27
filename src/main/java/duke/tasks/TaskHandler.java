package duke.tasks;

import duke.ui.Ui;
import duke.enums.Commands;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidOptionException;
import duke.util.DateFormatter;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskHandler {

    public static void addTask(Commands command, String input, ArrayList<Task> taskList) throws DukeException {
        int numberOfTasks = taskList.size();
        LocalDate date;

        switch (command) {
        case TODO:
            Task todo = new Todo(input);
            taskList.add(todo);
            numberOfTasks += 1;
            Ui.showTaskAddedText(todo.toString(), numberOfTasks);
            break;
        case DEADLINE:
            int indexOfBy = input.trim().indexOf("/by");

            if (indexOfBy == 0) {
                throw new InvalidOptionException("DEADLINE");
            }

            String deadlineMessage = input.substring(0, indexOfBy);
            String by = input.substring(indexOfBy + 4);

            date = DateFormatter.encodeDate(by);

            Task deadline = new Deadline(deadlineMessage, date);
            taskList.add(deadline);
            numberOfTasks += 1;
            Ui.showTaskAddedText(deadline.toString(), numberOfTasks);
            break;
        case EVENT:
            int indexOfAt = input.trim().indexOf("/at");

            if (indexOfAt == 0) {
                throw new InvalidOptionException("EVENT");
            }

            String eventMessage = input.substring(0, indexOfAt);
            String at = input.substring(indexOfAt + 4);

            date = DateFormatter.encodeDate(at);

            Task event = new Event(eventMessage, date);
            taskList.add(event);
            numberOfTasks += 1;
            Ui.showTaskAddedText(event.toString(), numberOfTasks);
            break;
        }
    }

    public static void listTasks(ArrayList<Task> taskList) {
        Ui.showLine();

        if (taskList.isEmpty()) {
            Ui.showMessageWithIndentation("You have not added any tasks yet.");
        }

        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            Ui.showMessageWithIndentation(index + ". " + taskList.get(i).toString());
        }

        Ui.showLine();
    }

    public static void doneTask(String input, ArrayList<Task> taskList) {
        int index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        Ui.showMessageBetweenLines("Nice! I've marked this task as done:", task.toString());
    }

    public static void deleteTask(String input, ArrayList<Task> taskList) {
        int index = Integer.parseInt(input) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        Integer numberOfTasks = taskList.size();

        Ui.showTaskDeletedText(task.toString(), numberOfTasks);
    }
}
