/**
 * DukeCommand is an enum class that allows execution of methods based on the command that user have typed in.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public enum DukeCommand {
    DEADLINE {
        /**
         * This method process the user's input parameters and instantiate new Deadline object, which is then added to the tasks list.
         * @param actions parameters of the new Deadline object.
         * @param taskList list of tasks to be maintained.
         * @throws DukeException whenever the user's input parameter is empty or entered an invalid format.
         */
        @Override
        public void runCommand(String actions, List<Task> taskList) throws DukeException, IOException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            if (!actions.contains("/by")) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            String[] inputs = actions.split("/by");
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            String description = inputs[0].trim();
            String date = inputs[1].trim();

            if(!checkIsValidDate(date)) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid date format.");
            }

            Deadline newDeadline = new Deadline(description, date);
            addCommand(newDeadline, taskList);
        }
    },
    DELETE {
        /**
         * This method handles the deletion of a task from the user's tasks list.
         * @param actions index of the task to be deleted.
         * @param taskList list of tasks to be maintained.
         * @throws DukeException whenever the user keyed in an invalid index.
         */
        @Override
        public void runCommand(String actions, List<Task> taskList) throws DukeException, IOException {
            int index = Integer.parseInt(actions) - 1;
            int listSize = taskList.size();
            if (listSize <= 0) {
                throw new DukeException("☹ OOPS!!! Your tasks list is empty.");
            }

            if (index < 0 || index >= listSize) {
                throw new DukeException("☹ OOPS!!! The number you entered is invalid.");
            }

            Task selectedTask = taskList.get(index);
            taskList.remove(selectedTask);
            System.out.println("Noted. I've removed this task:");
            System.out.printf(PRINT_FORMAT, selectedTask.toString());
            System.out.printf("Now you have %d %s in the list.%n", taskList.size(), taskList.size() >= 2 ? "tasks" : "task");
            saveFile(taskList);
        }
    },
    DONE {
        /**
         * This method handles the completion of a task from the user's tasks list.
         * @param actions index of the completed Task.
         * @param taskList list of tasks to be maintained.
         * @throws DukeException whenever the user keyed in an invalid index or trying to complete a completed task.
         */
        @Override
        public void runCommand(String actions, List<Task> taskList) throws DukeException, IOException {
            int index = Integer.parseInt(actions) - 1;
            int listSize = taskList.size();

            if (listSize <= 0) {
                throw new DukeException("☹ OOPS!!! Your tasks list is empty.");
            }
            if (index < 0 || index >= listSize) {
                throw new DukeException("☹ OOPS!!! The number you entered is invalid.");
            }

            Task selectedTask = taskList.get(index);
            if (selectedTask.getDone()) {
                throw new DukeException("☹ OOPS!!! You have already completed this task!");
            }
            selectedTask.setDone();

            System.out.println("Nice! I've marked this task as done:");
            System.out.printf(PRINT_FORMAT, selectedTask.toString());
            saveFile(taskList);
        }
    },
    EVENT {
        /**
         * This method process the user's input parameters and instantiate new Event object, which is then added to the tasks list.
         * @param actions parameters of the new Event object.
         * @param taskList list of tasks to be maintained.
         * @throws DukeException whenever the user's input parameter is empty or entered an invalid format.
         */
        @Override
        public void runCommand(String actions, List<Task> taskList) throws DukeException, IOException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            if (!actions.contains("/at")) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            String[] inputs = actions.split("/at");
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            String description = inputs[0].trim();
            String date = inputs[1].trim();
            if(!checkIsValidDate(date)) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid date format.");
            }

            Event newEvent = new Event(description, date);
            addCommand(newEvent, taskList);
        }
    },
    LIST {
        /**
         * This method prints the tasks that the user have saved so far.
         * @param actions inputs after the command.
         * @param taskList the list of task to be printed out.
         * @throws DukeException whenever there are additional inputs after the command or the list is empty.
         */
        @Override
        public void runCommand(String actions, List<Task> taskList) throws DukeException {
            int listSize = taskList.size();
            if (listSize <= 0) {
                throw new DukeException("☹ OOPS!!! Your tasks list is empty.");
            }

            //Clone the task list for filtering
            List<Task> printTaskList = new ArrayList<>(taskList);
            //If there is date in the command, only display the events or deadlines on the particular date.
            if (!actions.isEmpty() || !actions.isBlank()) {
                LocalDate queryDate = LocalDate.parse(actions);
                printTaskList.removeIf(t -> {
                    if (t instanceof Deadline) {
                        return !(((Deadline) t).by.isEqual(queryDate));
                    } else if (t instanceof Event) {
                        return !((Event) t).at.isEqual(queryDate);
                    }
                    return true;
                });
            }

            if (printTaskList.size() <= 0) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                throw new DukeException(String.format("☹ OOPS!!! You have no task on %s.", formatter.format(LocalDate.parse(actions))));
            }
            System.out.printf("Here are the %s in your list:%n", printTaskList.size() >= 2 ? "tasks" : "task");
            for (int i = 0; i < printTaskList.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, printTaskList.get(i).toString());
            }
        }
    },
    TODO {
        /**
         * This method process the user's input parameters and instantiate new ToDo object, which is then added to the tasks list.
         * @param actions parameters of the new ToDo object.
         * @param taskList list of tasks to be maintained.
         * @throws DukeException whenever the user's input parameter is empty.
         */
        @Override
        public void runCommand(String actions, List<Task> taskList) throws DukeException, IOException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            ToDo newToDo = new ToDo(actions);
            addCommand(newToDo, taskList);
        }
    };

    protected final static String PRINT_FORMAT = "\t%s%n";

    public abstract void runCommand(String actions, List<Task> taskList) throws DukeException, IOException;

    /**
     * This method handles the adding of Event, Deadline or ToDo into the list of tasks.
     * @param newTask new Event, Deadline or ToDo.
     * @param taskList list of tasks to be maintained.
     */
    protected void addCommand(Task newTask, List<Task> taskList) throws IOException {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.printf(PRINT_FORMAT, newTask.toString());
        System.out.printf("Now you have %d %s in the list.%n", taskList.size(), taskList.size() >= 2 ? "tasks" : "task");
        saveFile(taskList);
    }

    /**
     * This method checks whether the user's input is a valid command.
     * @param value input command from the user.
     * @return the boolean to indicate whether is it a valid command.
     */
    public static boolean isContains(String value) {
        for (DukeCommand cmd : values()) {
            if (cmd.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIsValidDate(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }


    protected static void saveFile(List<Task> taskList) throws IOException {
        String folderPath = "./data/";
        String filePath = "duke.txt";
        String dirPath = folderPath.concat(filePath);

        //Check if folder exists
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        FileWriter fileWriter = new FileWriter(dirPath);
        for(Task t : taskList) {
            fileWriter.write(String.format("%s%n", t.toStorageString()));
        }
        fileWriter.close();
    }
}

