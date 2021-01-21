/**
 * DukeCommand is an enum class that allows execution of methods based on the command that user have typed in.
 */

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
        public void runCommand(String actions, List<Task> taskList) throws DukeException {
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

            Deadline newDeadline = new Deadline(inputs[0].trim(), inputs[1].trim());
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
        public void runCommand(String actions, List<Task> taskList) throws DukeException {
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
        public void runCommand(String actions, List<Task> taskList) throws DukeException {
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
        public void runCommand(String actions, List<Task> taskList) throws DukeException {
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

            Event newEvent = new Event(inputs[0].trim(), inputs[1].trim());
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
            if (!actions.isEmpty() || !actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! I don't understand your additional command!");
            }

            int listSize = taskList.size();
            if (listSize <= 0) {
                throw new DukeException("☹ OOPS!!! Your tasks list is empty.");
            }

            System.out.printf("Here are the %s in your list:%n", taskList.size() >= 2 ? "tasks" : "task");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, taskList.get(i).toString());
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
        public void runCommand(String actions, List<Task> taskList) throws DukeException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            ToDo newToDo = new ToDo(actions);
            addCommand(newToDo, taskList);
        }
    };

    protected final static String PRINT_FORMAT = "\t%s%n";

    public abstract void runCommand(String actions, List<Task> taskList) throws DukeException;

    /**
     * This method handles the adding of Event, Deadline or ToDo into the list of tasks.
     * @param newTask new Event, Deadline or ToDo.
     * @param taskList list of tasks to be maintained.
     */
    protected void addCommand(Task newTask, List<Task> taskList) {
        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.printf(PRINT_FORMAT, newTask.toString());
        System.out.printf("Now you have %d %s in the list.%n", taskList.size(), taskList.size() >= 2 ? "tasks" : "task");
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
}
