package ssagit.parser;

import ssagit.datevalidator.DateValidator;
import ssagit.taskclass.DeadlineTask;
import ssagit.taskclass.EventTask;
import ssagit.taskclass.Task;
import ssagit.ui.ConsoleUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    ConsoleUI ui;
    boolean isBye;
    DateValidator validator;

    public Parser(ConsoleUI ui, DateValidator validator) {
        this.ui = ui;
        this.isBye = false;
        this.validator = validator;
    }

    /**
     * Exception class for missing todoTask descriptor.
     */
    static class MissingTodoDescriptorException extends Exception {
        public MissingTodoDescriptorException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * Exception class for unknown input parameters.
     */
    static class UnknownInputParamException extends Exception {
        public UnknownInputParamException(String errorMessage) {
            super(errorMessage);
        }
    }

    public boolean getIsBye() {
        return this.isBye;
    }

    /**
     * Parses lines of user input and outputs corresponding command.
     * @param tasks List of tasks from file.
     * @param taskIterator Integer to count number of tasks at a time.
     */
    public void parseInput(Task[] tasks, int taskIterator) {
        String input = ui.nextLine();
        String inputArr[] = input.split(" ", 2);

        try {
            switch (inputArr[0]) {
            case "bye":
                this.isBye = true;
                break;
            case "list":
                ui.list(tasks);
                break;
            case "done":
                int taskNum = Integer.parseInt(inputArr[1]) - 1;
                tasks[taskNum].markDone();
                ui.markDone(tasks[taskNum].toFormattedString());
                break;
            case "todo":
            case "event":
            case "deadline":
                // add to list
                String[] inputArrTasks = input.split("/", 2);
                String[] firstHalf = inputArrTasks[0].split(" ", 2);
                if (inputArrTasks.length != 1) {
                    // create Deadline/Event
                    String[] secondHalf = inputArrTasks[1].split(" ", 2);
                    if (validator.isValid(secondHalf[1])) {
                        Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
                        if (inputArr[0].equals("event")) {
                            tasks[taskIterator] = new EventTask(firstHalf[1], false, secondHalf[1].trim(), date);
                        } else if (inputArr[0].equals("deadline")) {
                            tasks[taskIterator] = new DeadlineTask(firstHalf[1], false, secondHalf[1].trim(), date);
                        }
                    } else {
                        System.out.println("Invalid date format for timed Task");
                    }
                } else {
                    // create todoTask
                    if (firstHalf.length == 1) {
                        throw new MissingTodoDescriptorException("------------------------------------\n" +
                                ":( OOPS!!! The description of a todo cannot be empty\n" +
                                "------------------------------------");
                    } else {
                        tasks[taskIterator] = new Task(firstHalf[1], false);
                    }
                }
                break;
            case "delete":
                int removeIndex = Integer.parseInt(inputArr[1]);
                taskIterator--; // reduce task count in list
                ui.deleteTaskMessage(tasks[removeIndex - 1].toFormattedString(), taskIterator);
                // actually delete the task and move all other tasks forward
                for (int i = removeIndex - 1; i < tasks.length - 1; i++) {
                    tasks[i] = tasks[i + 1];
                }
                break;
            case "find":
                String toFind = inputArr[1];
                String output = "Here are the matching tasks in your list:\n";
                for (Task t : tasks) {
                    if (t != null) {
                        if (t.getTaskName().contains(toFind)) {
                            output = output + t.toFormattedString() + "\n";
                        }
                    }
                }
                ui.formatBox(output);
                break;
            default:
                throw new UnknownInputParamException("------------------------------------\n" +
                        ":( OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "------------------------------------");
            }
        } catch (MissingTodoDescriptorException e) {
            System.out.println(e.getMessage());
        } catch (UnknownInputParamException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
