package bob.command;

import bob.DukeException;
import bob.Parser;
import bob.Storage;
import bob.TaskList;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.util.ArrayList;

/**
 * Represents the different types of possible commands.
 */
public enum Command {
    BYE {
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            return "Bye! See you soon!";
        }
    },
    LIST {
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            String message = "This is your list of tasks:\n";
            message += taskList.toString();
            return message;
        }
    },
    FIND {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            String message = "";
            try {
                String name = parser.parseName(userInput);
                TaskList findTask = new TaskList();
                ArrayList<Task> currTaskList = taskList.getTaskList();
                for (Task task : currTaskList) {
                    String taskName = task.getName();
                    if (taskName.contains(name)) {
                        findTask.addTask(task);
                    }
                }
                if (findTask.getSize() != 0) {
                    message = "Here are your search results: \n"
                            + findTask.toString();
                } else {
                    message = "Oops, there is no matching task!";
                }
            } catch (DukeException e) {
                message = e.getMessage();
            }
            return message;
        }
    },
    DONE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int index = parser.parseNumber(userInput, 5);
                Task updatedTask = taskList.changeStatus(index - 1, true);
                return "Good job! This task has been marked as done :)\n" + updatedTask.toString();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    },
    DELETE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int index = parser.parseNumber(userInput, 7);
                Task removedTask = taskList.removeTask(index - 1);
                return "Alright, this task has been removed.\n" + removedTask
                        + "\nThere are " + taskList.getSize() + " tasks left.";
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    },
    TODO {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                String taskName = parser.parseName(userInput);
                Todo newTodo = new Todo(taskName);
                taskList.addToDo(newTodo);
                storage.appendToDo(newTodo);
                return "Alright, I have added this new todo.\n"
                        + newTodo + "\n" + "There are a total of "
                        + taskList.getSize() + " tasks now.";
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    },
    EVENT {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int taskIndex = userInput.indexOf("/at");
                if (taskIndex != -1) {
                    Event newEvent = parser.parseEvent(userInput, taskIndex);
                    taskList.addEvent(newEvent);
                    storage.appendEvent(newEvent);
                    return "Alright, I have added this new event.\n"
                            + newEvent + "\n" + "There is a total of "
                            + taskList.getSize() + " tasks now.";
                } else {
                    return "There is no event timing detected!\n"
                            + "Please try again with a correct format";
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    },
    DEADLINE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int deadlineIndex = userInput.indexOf("/by");
                if (deadlineIndex != -1) {
                    Deadline newDeadline = parser.parseDeadline(userInput, deadlineIndex);
                    taskList.addDeadline(newDeadline);
                    storage.appendDeadline(newDeadline);
                    return "Alright, I have added this new deadline.\n"
                            + newDeadline + "\n" + "There is a total of "
                            + taskList.getSize() + " tasks now.";
                } else {
                    return "There is no deadline time and date detected!\n"
                            + "Please try again with a correct format";
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    },
    INVALID {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            return "Sorry, I have no idea what that means :( Please try again!";
        }
    };

    Parser parser = new Parser();

    public abstract String executeCommand(String userInput, TaskList taskList, Storage storage);
}