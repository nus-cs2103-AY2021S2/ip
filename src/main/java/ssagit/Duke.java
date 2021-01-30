package ssagit;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import ssagit.storage.Storage;
import ssagit.ui.ConsoleUI;
import ssagit.taskclass.*;
import ssagit.datevalidator.DateValidator;
import ssagit.datevalidator.DateValidatorLocalDate;

public class Duke {
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

    public static void main(String[] args) {
        try {
            /** Handler for all UI stuff. */
            ConsoleUI ui = new ConsoleUI(System.in);
            ui.introduction();

            // inits
            Task[] taskArr = new Task[100];
            String input;
            // date init
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
            DateValidator validator = new DateValidatorLocalDate(dateFormatter);
            // storage init
            Storage storage = new Storage("./ssagit/data/taskList.txt");
            int taskIterator = storage.readTaskListToArray(taskArr, validator);

            while (true) {
                input = ui.nextLine();
                String inputArr[] = input.split(" ");
                if (inputArr[0].equals("bye"))
                    break;
                else if (inputArr[0].equals("list")) {
                    ui.list(taskArr);
                } else if (inputArr[0].equals("done")) {
                    int taskNum = Integer.parseInt(inputArr[1]) - 1;
                    taskArr[taskNum].markDone();
                    ui.markDone(taskArr[taskNum].toFormattedString());
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("event") ||
                        inputArr[0].equals("deadline")) {
                    // add to list
                    String[] inputArrTasks = input.split("/", 2);
                    String[] firstHalf = inputArrTasks[0].split(" ", 2);
                    if (inputArrTasks.length != 1) {
                        // create Deadline/Event
                        String[] secondHalf = inputArrTasks[1].split(" ", 2);
                        if (validator.isValid(secondHalf[1])) {
                            Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(secondHalf[1]);
                            if (inputArr[0].equals("event")) {
                                taskArr[taskIterator] = new EventTask(firstHalf[1], false, secondHalf[1].trim(), date);
                            } else if (inputArr[0].equals("deadline")) {
                                taskArr[taskIterator] = new DeadlineTask(firstHalf[1], false, secondHalf[1].trim(), date);
                            }
                        } else {
                            System.out.println("Invalid date format for timed Task");
                            continue;
                        }
                    } else {
                        // create todoTask
                        if (firstHalf.length == 1) {
                            throw new MissingTodoDescriptorException("------------------------------------\n" +
                                    ":( OOPS!!! The description of a todo cannot be empty\n" +
                                    "------------------------------------");
                        } else {
                            taskArr[taskIterator] = new Task(firstHalf[1], false);
                        }
                    }
                    taskIterator++; // increase task count in list
                    /** show message to user when task is added. */
                    ui.addTaskMessage(taskArr[taskIterator - 1].toFormattedString(), taskIterator);
                } else if (inputArr[0].equals("delete")) {
                    int removeIndex = Integer.parseInt(inputArr[1]);
                    taskIterator--; // reduce task count in list
                    ui.deleteTaskMessage(taskArr[removeIndex - 1].toFormattedString(), taskIterator);
                    // actually delete the task and move all other tasks forward
                    for (int i = removeIndex - 1; i < taskArr.length - 1; i++) {
                        taskArr[i] = taskArr[i + 1];
                    }
                } else {
                    throw new UnknownInputParamException("------------------------------------\n" +
                            ":( OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "------------------------------------");
                }
            }
            // always ensure file is present before leaving program
            storage.writeTasks(taskArr);
            ui.bye();
        } catch (MissingTodoDescriptorException e) {
            System.out.println(e.getMessage());
        } catch (UnknownInputParamException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
