import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void list() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.println("     " + (i + 1) + "." + currTask.toString());
        }
    }

    public void done(String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        if (inputArr.length < 3) {
            if (inputArr.length == 1) {
                throw new DukeException("       OOPS!!! The task number cannot be empty.");
            } else if (!isNumeric(inputArr[1])) {
                throw new DukeException("       OOPS!!! The task number must be numeric.");
            } else {
                int i = Integer.parseInt(inputArr[1]) - 1;
                if (i > (this.tasks.size() - 1) || i < 0) {
                    throw new DukeException("       OOPS!!! The task number is out of range. "
                            + "Please use \"list\" to see the list of tasks.");
                } else {
                    this.tasks.get(i).markAsDone();
                    Task currTask = tasks.get(i);
                    System.out.println("     Nice! I've marked this task as done:\n"
                            + "       " + currTask.toString());
                }
            }
        } else {
            throw new DukeException("       OOPS!!! The format should be \"done #\".");
        }
    }

    public void delete(String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        if (inputArr.length < 3) {
            if (inputArr.length == 1) {
                throw new DukeException("       OOPS!!! The task number cannot be empty.");
            } else if (!isNumeric(inputArr[1])) {
                throw new DukeException("       OOPS!!! The task number must be numeric.");
            } else {
                int i = Integer.parseInt(inputArr[1]) - 1;
                if (i > (this.tasks.size() - 1) || i < 0) {
                    throw new DukeException("       OOPS!!! The task number is out of range. "
                            + "Please use \"list\" to see the list of tasks.");
                } else {
                    System.out.println("     Noted. I've removed this task:\n"
                            + "       " + this.tasks.remove(i).toString() + "\n"
                            + "     Now you have " + this.tasks.size() + " tasks in the list.");
                }
            }
        } else {
            throw new DukeException("       OOPS!!! The format should be \"delete #\".");
        }
    }

    public void addTask(String type, String fullCommand) throws DukeException {
        String[] inputArr = fullCommand.split(" ");
        switch (type) {
            case "todo":
                if (inputArr.length == 1) {
                    throw new DukeException("       OOPS!!! The description of a todo cannot be empty.");
                } else {
                    this.tasks.add(new Todo(0, fullCommand.substring(5)));
                }
                break;
            case "deadline":
                if (inputArr.length == 1) {
                    throw new DukeException("       OOPS!!! The description and due date "
                            + "of a deadline cannot be empty.");
                } else {
                    String[] details = fullCommand.substring(9).split(" /by ");
                    if (details.length == 1) {
                        throw new DukeException("       OOPS!!! The due date of a deadline "
                                + "cannot be empty.");
                    } else {
                        try {
                            this.tasks.add(new Deadline(0, details[0], LocalDate.parse(details[1])));
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("       OOPS!!! The date has to be in the format yyyy-mm-dd.");
                        }
                    }
                }
                break;
            case "event":
                if (inputArr.length == 1) {
                    throw new DukeException("       OOPS!!! The description and time frame "
                            + "of an event cannot be empty.");
                } else {
                    String[] details = fullCommand.substring(6).split(" /at ");
                    if (details.length == 1) {
                        throw new DukeException("       OOPS!!! The time frame of an event "
                                + "cannot be empty.");
                    } else {
                        try {
                            this.tasks.add(new Event(0, details[0], LocalDate.parse(details[1])));
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("       OOPS!!! The date has to be in the format yyyy-mm-dd.");
                        }
                    }
                }
                break;
        }
        System.out.println("     Got it. I've added this task:\n"
                + "       " + this.tasks.get(this.tasks.size() - 1).toString() + "\n"
                + "     Now you have " + this.tasks.size() + " tasks in the list.");
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
