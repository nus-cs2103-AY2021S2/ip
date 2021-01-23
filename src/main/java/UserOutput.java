import java.util.List;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class UserOutput {
    public boolean verboseFlag;

    public UserOutput(boolean verboseFlag) {
        this.verboseFlag = verboseFlag;
    }

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
            case "markDone":
                Task task;
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
            case "printTaskList":
                if (pair.getSecondItem() instanceof List) {
                    taskList = (List<Task>)pair.getSecondItem();
                    this.printTaskList(taskList);
                    break;
                } else {
                    throw new ClassCastException("Invalid class casting. Not stonks!");
                }
        }
    }

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

    public void printInitialGreeting() {
        System.out.println("I am Meme Man. Whoms't be entering the VIMension?");
        if (this.verboseFlag) {
            System.out.println("I speak a lot. For I am verbose!\n");
        } else {
            System.out.println("\n");
        }
    }

    public void printFileTasksAdded(List<Task> taskList) {
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

    public void printUserTaskAdded(Task task, int total) {
        String taskType = task.getType();
        String printTaskType = this.spellTaskType(taskType);
        System.out.printf("Meme Man has added %s task: %s\n", printTaskType, task);
        System.out.printf("Total number of tasks: %d\n\n", total);
    }

    public void printTaskDone(Task doneTask) {
        System.out.println("Stonks! You've done this task:");
        System.out.println(doneTask.getDescription() + "\n");
    }

    public void printTaskUndone(Task undoneTask) {
        System.out.println("Not stonks! This task has been marked as undone:");
        System.out.println(undoneTask.getDescription() + "\n");
    }

    public void printTaskDelete(Task deletedTask, int numberOfTasks) {
        System.out.println("This task has been deleted:");
        System.out.println(deletedTask);
        System.out.printf("Total number of tasks: %d\n\n", numberOfTasks);
    }

    public void printTaskList(List<Task> taskList) {
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

    public void printExitProgram() {
        if (this.verboseFlag) {
            System.out.println("Saving tasks now...");
        }
        System.out.println("You have been EJECTED!");
    }

    public void printOrang() {
        System.err.println("Meme Man: ORANG! IT S U...");
        System.err.println("Orang: No you can't SU");
        System.err.println("Meme Man: ANGERY!\n");
    }

    public void printVegetal() {
        System.err.println("Vegetal: Did someone said... NO VEGETALS?");
        System.err.println("Meme Man: I taste a vegetal... ANGERY!\n");
    }

    public void printException(Exception e) {
        System.err.println(e.getMessage() + "\n");
    }
}
