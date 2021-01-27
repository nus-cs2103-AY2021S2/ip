package surrealchat.user;

import surrealchat.Pair;
import surrealchat.task.Task;

import java.util.List;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Handles printing of output.
 */
public class UserOutput {
    protected boolean verboseFlag;

    /**
     * Creates new UserOutput instance.
     * @param verboseFlag Flag for whether additional output should be printed.
     */
    public UserOutput(boolean verboseFlag) {
        this.verboseFlag = verboseFlag;
    }

    /**
     * Converts task type from one capital letter to full lowercase spelling.
     * @param taskType Type of task in one capital letter.
     * @return Type of task in full lowercase spelling.
     */
    public String spellTaskType(String taskType) {
        switch(taskType) {
        case "T":
            return "todo";
        case "D":
            return "deadline";
        case "E":
            return "event";
        default:
            throw new InputMismatchException("The task type in task is invalid. Not Stonks!");
        }
    }

    /**
     * Calls the appropriate print methods based on string tag of pair.
     * @param pair Pair consisting of a string tag and a generic object.
     * @param <U> Generic type of second item in pair.
     */
    public <U> void printOutput(Pair<String, U> pair) {
        String printType = pair.getFirstItem();
        switch(printType) {
        case "userTaskAdded":
            Pair<Task, Integer> intermediatePair;
            if (pair.getSecondItem() instanceof Pair) {
                intermediatePair = (Pair<Task, Integer>)pair.getSecondItem();
                this.printUserTaskAdded(intermediatePair.getFirstItem(), intermediatePair.getSecondItem());
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "fileTasksAdded":
            List<Task> taskList;
            if (pair.getSecondItem() instanceof List) {
                taskList = (List<Task>)pair.getSecondItem();
                this.printFileTasksAdded(taskList);
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "editDescript":
            Task task;
            if (pair.getSecondItem() instanceof Task) {
                task = (Task)pair.getSecondItem();
                this.printEditDescription(task);
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "markDone":
            if (pair.getSecondItem() instanceof Task) {
                task = (Task)pair.getSecondItem();
                this.printTaskDone(task);
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "markUndone":
            if (pair.getSecondItem() instanceof Task) {
                task = (Task)pair.getSecondItem();
                this.printTaskUndone(task);
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "deleteTask":
            if (pair.getSecondItem() instanceof Pair) {
                intermediatePair = (Pair<Task, Integer>)pair.getSecondItem();
                this.printTaskDelete(intermediatePair.getFirstItem(), intermediatePair.getSecondItem());
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "searchTasks":
            if (pair.getSecondItem() instanceof List) {
                List<Task> searchResults = (List<Task>)pair.getSecondItem();
                this.printSearchResults(searchResults);
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        case "printTaskList":
            if (pair.getSecondItem() instanceof List) {
                taskList = (List<Task>)pair.getSecondItem();
                this.printTaskList(taskList);
                break;
            } else {
                throw new ClassCastException("Invalid class casting. Not stonks!");
            }
        default:
            throw new UnsupportedOperationException("Somehow I got an invalid tag. Not stonks!");
        }
    }

    /**
     * Calls the appropriate print methods based on what easter egg user has activated.
     * @param easterEgg Command for the appropriate easter egg.
     */
    public void printEasterEggOutput(String easterEgg) {
        switch(easterEgg){
        case "orang":
            this.printOrang();
            break;
        case "vegetal":
            this.printVegetal();
            break;
        default:
            throw new InputMismatchException("Somehow, the easter egg input does not match. Not stonks!");
        }
    }

    /**
     * Prints out a greeting upon start up.
     */
    public void printInitialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?");
        if (this.verboseFlag) {
            System.out.println("I speak a lot. For I am verbose!\n");
        } else {
            System.out.println("\n");
        }
    }

    private void printFileTasksAdded(List<Task> taskList) {
        if (this.verboseFlag) {
            int total = taskList.size();
            for (int i = 0; i < total; i++) {
                Task task = taskList.get(i);
                String printTaskType = this.spellTaskType(task.getType());
                System.out.printf("Meme Man has added %s task from file: %s\n", printTaskType, task);
            }
            System.out.printf("Total number of tasks loaded from file: %s\n\n", total);
        }
    }

    private void printUserTaskAdded(Task task, int total) {
        String taskType = task.getType();
        String printTaskType = this.spellTaskType(taskType);
        System.out.printf("Meme Man has added %s task: %s\n", printTaskType, task);
        System.out.printf("Total number of tasks: %d\n\n", total);
    }

    private void printEditDescription(Task editTask) {
        System.out.println("You have edited a task description to this:");
        System.out.println(editTask.getDescription() + "\n");
    }

    private void printTaskDone(Task doneTask) {
        System.out.println("Stonks! You've done this task:");
        System.out.println(doneTask.getDescription() + "\n");
    }

    private void printTaskUndone(Task undoneTask) {
        System.out.println("Not stonks! This task has been marked as undone:");
        System.out.println(undoneTask.getDescription() + "\n");
    }

    private void printTaskDelete(Task deletedTask, int numberOfTasks) {
        System.out.println("This task has been deleted:");
        System.out.println(deletedTask);
        System.out.printf("Total number of tasks: %d\n\n", numberOfTasks);
    }

    private void printTaskList(List<Task> taskList) {
        if (taskList.isEmpty()) {
            throw new NoSuchElementException("I have nothing to print. Not stonks!");
        } else {
            System.out.println("I print the tasks:");
            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(i + ". " + taskList.get(i - 1));
            }
            System.out.println("Hmmst've... Stonks\n");
        }
    }

    private void printSearchResults(List<Task> searchResults) {
        if (searchResults.isEmpty()) {
            throw new NoSuchElementException("My search returned nothing. Not stonks!");
        } else {
            System.out.println("Here are my search results:");
            for (int i = 1; i <= searchResults.size(); i++) {
                System.out.println(i + ". " + searchResults.get(i - 1));
            }
            System.out.println("Hmmst've... Stonks\n");
        }
    }

    /**
     * Prints out the farewell message upon program exiting normally.
     */
    public void printExitProgram() {
        if (this.verboseFlag) {
            System.out.println("Saving tasks now...");
        }
        System.out.println("You have been EJECTED!");
    }

    private void printOrang() {
        System.err.println("Meme Man: ORANG! IT S U...");
        System.err.println("Orang: No you can't SU");
        System.err.println("Meme Man: ANGERY!\n");
    }

    private void printVegetal() {
        System.err.println("Vegetal: Did someone said... NO VEGETALS?");
        System.err.println("Meme Man: I taste a vegetal... ANGERY!\n");
    }

    /**
     * Prints out exception message.
     * @param e Any caught exception.
     */
    public void printException(Exception e) {
        System.err.println(e.getMessage() + "\n");
    }
}
