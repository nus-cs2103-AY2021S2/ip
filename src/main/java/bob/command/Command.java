package bob.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import bob.BobException;
import bob.processor.Parser;
import bob.processor.Storage;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.Todo;

/**
 * Represents the different types of possible commands.
 */
public enum Command {
    HELP {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            return "You can add a new task using the following commands: \n" +
                    "todo NAME_OF_TASK\n" +
                    "event NAME_OF_EVENT /at: YYYY-MM-DD HHMM\n" +
                    "deadline NAME_OF_DEADLINE /by: YYYY-MM-DD HHMM\n" +
                    "\nYou can see the whole list of tasks using: list\n" +
                    "\nTo search for a specific task: find KEYWORDS\n" +
                    "\nTo add a reminder for a task:\n" +
                    "remind INDEX /on: YYYY-MM-DD HHMM\n" +
                    "\nTo mark a task as done: done INDEX\n" +
                    "\nTo delete a task: delete INDEX\n" +
                    "\nTo exit the app: bye";
        }
    },
    REMIND {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int index = parser.parseNumber(userInput);
                Task currentTask = taskList.getTaskList().get(index - 1);
                LocalDateTime remindDateTime = parser.parseDateTime(userInput, "remind");
                System.out.println(remindDateTime.toString() + " " + LocalDateTime.now().toString());
                if (remindDateTime.isBefore(LocalDateTime.now()) || remindDateTime.isEqual(LocalDateTime.now())) {
                    return "Please enter a valid timing!";
                }
                taskList.addReminder(remindDateTime, currentTask);
                if (currentTask.getReminderDateTime() != null) {
                    taskList.removeReminder(currentTask.getReminderDateTime(), currentTask);
                }
                currentTask.addReminder(remindDateTime);
                storage.rewrite(taskList);
                return "A new reminder is added to " + currentTask.toString() + " on: " + remindDateTime.toString();
            } catch (BobException e) {
                return e.getMessage();
            }
        }
    },
    BYE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            return "Bye! See you soon!";
        }
    },
    LIST {
        @Override
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
                    String taskName = task.getName().toLowerCase();
                    if (taskName.contains(name.toLowerCase())) {
                        findTask.addTask(task);
                    }
                }
                if (findTask.getSize() != 0) {
                    message = "Here are your search results: \n"
                            + findTask.toString();
                } else {
                    message = "Oops, there is no matching task!";
                }
            } catch (BobException e) {
                message = e.getMessage();
            }
            return message;
        }
    },
    DONE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int index = parser.parseNumber(userInput);
                Task updatedTask = taskList.changeStatus(index - 1, true);
                storage.rewrite(taskList);
                return "Good job! This task has been marked as done :)\n" + updatedTask.toString();
            } catch (BobException e) {
                return e.getMessage();
            }
        }
    },
    DELETE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int index = parser.parseNumber(userInput);
                Task removedTask = taskList.removeTask(index - 1);
                storage.rewrite(taskList);
                return "Alright, this task has been removed.\n" + removedTask
                        + "\nThere are " + taskList.getSize() + " tasks left.";
            } catch (BobException e) {
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
                storage.appendTask(newTodo);
                return "Alright, I have added this new todo.\n"
                        + newTodo + "\n" + "There are a total of "
                        + taskList.getSize() + " tasks now.";
            } catch (BobException e) {
                return e.getMessage();
            }
        }
    },
    EVENT {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int taskIndex = userInput.indexOf("/at:");
                if (taskIndex != -1) {
                    String name = parser.parseName(userInput);
                    LocalDateTime dateTime = parser.parseDateTime(userInput, "event");
                    Event newEvent = new Event(name, dateTime.toLocalDate(), dateTime.toLocalTime());
                    taskList.addEvent(newEvent);
                    storage.appendTask(newEvent);
                    return "Alright, I have added this new event.\n"
                            + newEvent + "\n" + "There is a total of "
                            + taskList.getSize() + " tasks now.";
                } else {
                    return "There is no event timing detected!\n"
                            + "Please try again with a correct format";
                }
            } catch (BobException e) {
                return e.getMessage();
            }
        }
    },
    DEADLINE {
        @Override
        public String executeCommand(String userInput, TaskList taskList, Storage storage) {
            try {
                int deadlineIndex = userInput.indexOf("/by:");
                if (deadlineIndex != -1) {
                    String name = parser.parseName(userInput);
                    LocalDateTime dateTime = parser.parseDateTime(userInput, "deadline");
                    Deadline newDeadline = new Deadline(name, dateTime.toLocalDate(), dateTime.toLocalTime());
                    taskList.addDeadline(newDeadline);
                    storage.appendTask(newDeadline);
                    return "Alright, I have added this new deadline.\n"
                            + newDeadline + "\n" + "There is a total of "
                            + taskList.getSize() + " tasks now.";
                } else {
                    return "There is no deadline time and date detected!\n"
                            + "Please try again with a correct format";
                }
            } catch (BobException e) {
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

    private static final Parser parser = new Parser();

    /**
     * Executes the required actions for each command.
     *
     * @param userInput User's input.
     * @param taskList The list of tasks user has.
     * @param storage The storage of the list of tasks in the hard disk.
     * @return
     */
    public abstract String executeCommand(String userInput, TaskList taskList, Storage storage);
}
