package surrealchat.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import surrealchat.exception.SurrealException;
import surrealchat.task.DeadlineTask;
import surrealchat.task.EventTask;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;
import surrealchat.task.TaskPriority;
import surrealchat.task.ToDoTask;

/**
 * Command object for editing parameters of Task objects.
 */
public class EditCommand extends Command {
    protected static final int TODO_ARGUMENTS = 2;
    protected static final int DEADLINE_EVENT_ARGUMENTS = 3;
    protected static final String DO_NOT_EDIT = "-keep-";
    protected final String rawDescription;

    /**
     * Creates a new EditCommand object
     * @param rawDescription The task number to edit + /edit tag + new parameters.
     */
    public EditCommand(String rawDescription) {
        super("edit");
        this.rawDescription = rawDescription;
    }

    private String printOutput(Task editedTask) {
        String outputString = "You have edited a task to this:\n";
        outputString += String.format("%s\n", editedTask);
        return outputString;
    }

    private boolean keepOriginal(String newArgument) {
        if (newArgument.equalsIgnoreCase(DO_NOT_EDIT)) {
            return true;
        } else {
            return false;
        }
    }

    private LocalDateTime parseDate(String dateString) throws SurrealException {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new SurrealException("Input date time format is incorrect. Not stonks!\n");
        }
    }

    private ToDoTask editToDo(ToDoTask toDoTask, String rawDescription) throws SurrealException {
        String[] splitArguments = rawDescription.split(";");
        if (splitArguments.length != TODO_ARGUMENTS) {
            throw new SurrealException(
                    "Wrong number of arguments! Please also check your formatting for ';'. Not stonks!\n");
        }

        String newDescription = splitArguments[0].trim();
        String newPriorityString = splitArguments[1].trim();
        boolean keepDescription = keepOriginal(newDescription);
        boolean keepPriority = keepOriginal(newPriorityString);
        try {
            if (keepDescription && keepPriority) {
                throw new SurrealException("There is no point editing this task! Not stonks!\n");
            } else if (keepPriority) {
                ToDoTask editedTask = toDoTask.editDescription(newDescription);
                return editedTask;
            } else if (keepDescription) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                ToDoTask editedTask = toDoTask.editPriority(newPriority);
                return editedTask;
            } else {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                ToDoTask editedTask = toDoTask.editTask(newDescription, newPriority);
                return editedTask;
            }
        } catch (NumberFormatException e) {
            throw new SurrealException(
                    "Priority number not parsed. Please check if you put anything other than int. Not stonks!\n");
        }
    }

    private DeadlineTask editDeadline(DeadlineTask task, String rawDescription) throws SurrealException {
        String[] splitArguments = rawDescription.split(";");
        if (splitArguments.length != DEADLINE_EVENT_ARGUMENTS) {
            throw new SurrealException(
                    "Wrong number of arguments! Please also check your formatting for ';'. Not stonks!\n");
        }

        String newDescription = splitArguments[0].trim();
        String newPriorityString = splitArguments[1].trim();
        String newDeadlineString = splitArguments[2].trim();
        boolean keepDescription = keepOriginal(newDescription);
        boolean keepPriority = keepOriginal(newPriorityString);
        boolean keepDeadline = keepOriginal(newDeadlineString);
        try {
            if (keepDescription && keepPriority && keepDeadline) {
                throw new SurrealException("There is no point editing this task! Not stonks!\n");
            } else if (keepPriority && keepDeadline) {
                DeadlineTask editedTask = task.editDescription(newDescription);
                return editedTask;
            } else if (keepDescription && keepDeadline) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                DeadlineTask editedTask = task.editPriority(newPriority);
                return editedTask;
            } else if (keepDescription && keepPriority) {
                LocalDateTime newDeadline = parseDate(newDeadlineString);
                DeadlineTask editedTask = task.editDeadline(newDeadline);
                return editedTask;
            } else if (keepDeadline) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                DeadlineTask editedTask = task.editDescription(newDescription).editPriority(newPriority);
                return editedTask;
            } else if (keepPriority) {
                LocalDateTime newDeadline = parseDate(newDeadlineString);
                DeadlineTask editedTask = task.editDescription(newDescription).editDeadline(newDeadline);
                return editedTask;
            } else if (keepDescription) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                LocalDateTime newDeadline = parseDate(newDeadlineString);
                DeadlineTask editedTask = task.editPriority(newPriority).editDeadline(newDeadline);
                return editedTask;
            } else {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                LocalDateTime newDeadline = parseDate(newDeadlineString);
                DeadlineTask editedTask = task.editTask(newDescription, newPriority, newDeadline);
                return editedTask;
            }
        } catch (NumberFormatException e) {
            throw new SurrealException(
                    "Priority number not parsed. Please check if you put anything other than int. Not stonks!\n");
        }
    }

    private EventTask editEvent(EventTask task, String rawDescription) throws SurrealException {
        String[] splitArguments = rawDescription.split(";");
        if (splitArguments.length != DEADLINE_EVENT_ARGUMENTS) {
            throw new SurrealException(
                    "Wrong number of arguments! Please also check your formatting for ';'. Not stonks!\n");
        }

        String newDescription = splitArguments[0].trim();
        String newPriorityString = splitArguments[1].trim();
        String newEventDatetimeString = splitArguments[2].trim();
        boolean keepDescription = keepOriginal(newDescription);
        boolean keepPriority = keepOriginal(newPriorityString);
        boolean keepEventDate = keepOriginal(newEventDatetimeString);
        try {
            if (keepDescription && keepPriority && keepEventDate) {
                throw new SurrealException("There is no point editing this task! Not stonks!\n");
            } else if (keepPriority && keepEventDate) {
                EventTask editedTask = task.editDescription(newDescription);
                return editedTask;
            } else if (keepDescription && keepEventDate) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                EventTask editedTask = task.editPriority(newPriority);
                return editedTask;
            } else if (keepDescription && keepPriority) {
                LocalDateTime newEventDate = parseDate(newEventDatetimeString);
                EventTask editedTask = task.editEventDate(newEventDate);
                return editedTask;
            } else if (keepEventDate) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                EventTask editedTask = task.editDescription(newDescription).editPriority(newPriority);
                return editedTask;
            } else if (keepPriority) {
                LocalDateTime newEventDate = parseDate(newEventDatetimeString);
                EventTask editedTask = task.editDescription(newDescription).editEventDate(newEventDate);
                return editedTask;
            } else if (keepDescription) {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                LocalDateTime newEventDate = parseDate(newEventDatetimeString);
                EventTask editedTask = task.editPriority(newPriority).editEventDate(newEventDate);
                return editedTask;
            } else {
                TaskPriority newPriority = TaskPriority.getPriorityType(Integer.valueOf(newPriorityString));
                LocalDateTime newEventDate = parseDate(newEventDatetimeString);
                EventTask editedTask = task.editTask(newDescription, newPriority, newEventDate);
                return editedTask;
            }
        } catch (NumberFormatException e) {
            throw new SurrealException(
                    "Priority number not parsed. Please check if you put anything other than int. Not stonks!\n");
        }
    }

    private static boolean isValidTaskType(String type) {
        return (type == "T") || (type == "D") || (type == "E");
    }

    private Task editTaskByType(Task task, String rawDescription) throws SurrealException {
        String taskType = task.getType();
        assert EditCommand.isValidTaskType(taskType) : "Invalid task type. Not stonks\n";

        switch(taskType) {
        case "T":
            return editToDo((ToDoTask) task, rawDescription);
        case "D":
            return editDeadline((DeadlineTask) task, rawDescription);
        case "E":
            return editEvent((EventTask) task, rawDescription);
        default:
            throw new SurrealException("Somehow assert statement in editTaskByType was bypassed. Not stonks!\n");
        }
    }

    /**
     * Executes edit command to edit Task parameters
     * @param taskManagement TaskManagement object where Task to be edited is stored.
     * @return String to describe the new task.
     */
    public String execute(TaskManagement taskManagement) {
        if (rawDescription.isEmpty()) {
            return "Did you forget to add the task number and new description? Not stonks!\n";
        }
        //Split the description into task number and description
        String[] descriptionSplitArray = rawDescription.split("/edit");
        try {
            int taskNumber = Integer.valueOf(descriptionSplitArray[0].trim());
            if (Command.isInvalidTaskNumber(taskNumber, taskManagement.getNumberOfTasks())) {
                throw new SurrealException("Invalid task number. Not stonks!\n");
            }

            String newDescription = descriptionSplitArray[1].trim();
            if (newDescription.isEmpty()) {
                throw new SurrealException("No arguments provided for editing. Not stonks!\n");
            }
            Task taskToEdit = taskManagement.getTask(taskNumber);
            Task editedTask = editTaskByType(taskToEdit, newDescription);
            taskManagement.replaceTask(taskNumber, editedTask);
            return printOutput(editedTask);
        } catch (NumberFormatException e) { //Can happen if clean split does not occur.
            return e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur.
            return "Wrong formatting. Did you forget to put '/edit' and/or the description? Not stonks!\n";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of edit command.
     * @return String describing the edit command.
     */
    public static String displayHelp() {
        String outputString = "Given a task number and new arguments, edits that task to have new parameters.\n";
        outputString += "Format of arguments for ToDo tasks: ";
        outputString += "edit [task number] /edit [new description] ; [new priority]\n";
        outputString += "Format of arguments for Deadline and Event tasks: ";
        outputString += "edit [task number] /edit [new description] ; [new priority] ; [new datetime]\n";
        outputString += "Use -keep- to preserve certain details.\n";
        return outputString;
    }
}
