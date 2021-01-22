import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDate;

public class TaskManagement {
    public static final String TASK_FILE_PATH = "tasks.txt";
    public List<Task> taskList;

    public TaskManagement(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addToDo(String taskDescription, int isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty todo task description. Not stonks!");
        } else {
            ToDoTask newTask = new ToDoTask(taskDescription.trim(), isDone);
            System.out.printf("Meme Man is now adding to-do task: %s\n", newTask);
            this.addTask(newTask);
        }
    }

    public void addDeadline(String taskDescription, int isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty deadline task description. Not stonks!");
        } else {
            String[] descriptionSplitArray = taskDescription.split("/by");
            try {
                DeadlineTask newTask = new DeadlineTask(descriptionSplitArray[0].trim(),
                        LocalDate.parse(descriptionSplitArray[1].trim()), isDone);
                System.out.printf("Meme Man is now adding deadline task: %s\n", newTask);
                this.addTask(newTask);
            } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
                throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/by'? Not stonks!");
            }
        }
    }

    public void addEvent(String taskDescription, int isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty event task description. Not stonks!");
        } else {
            String[] descriptionSplitArray = taskDescription.split("/at");
            try {
                EventTask newTask = new EventTask(descriptionSplitArray[0].trim(),
                        LocalDate.parse(descriptionSplitArray[1].trim()), isDone);
                System.out.printf("Meme Man is now adding event task: %s\n", newTask);
                this.addTask(newTask);
            } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
                throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/at'? Not stonks!");
            }
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.printf("Total number of tasks: %d\n\n", this.taskList.size());
    }

    public boolean checkInvalidTaskNumber(int taskNumber) {
        return ((taskNumber <= 0) || (taskNumber > this.taskList.size()));
    }

    public void markAsDone(int taskNumber) {
        if (this.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task doneTask = this.taskList.get(taskNumber - 1);
            doneTask.markAsDone();
            System.out.println("Stonks! You've done this task:");
            System.out.println(doneTask.getDescription() + "\n");
            this.taskList.set(taskNumber - 1, doneTask);
        }
    }

    public void markAsUndone(int taskNumber) {
        if (this.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task undoneTask = this.taskList.get(taskNumber - 1);
            undoneTask.markAsUndone();
            System.out.println("Not stonks! This task has been marked as undone:");
            System.out.println(undoneTask.getDescription() + "\n");
            this.taskList.set(taskNumber - 1, undoneTask);
        }
    }

    public void deleteTask(int taskNumber) {
        if (this.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task deletedTask = this.taskList.remove(taskNumber - 1);
            System.out.println("This task has been deleted:");
            System.out.println(deletedTask);
            System.out.printf("Total number of tasks: %d\n\n", this.taskList.size());
        }
    }

    public void printList() {
        if (this.taskList.isEmpty()) {
            throw new NoSuchElementException("I have nothing to print. Not stonks!");
        } else {
            System.out.println("I print the tasks:");
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println(i + ". " + this.taskList.get(i - 1));
            }
            System.out.println("Hmmst've... Stonks\n");
        }
    }

    public void saveTasksToFile() {
        try {
            FileWriter fw = new FileWriter(TaskManagement.TASK_FILE_PATH);
            for (int i = 0; i < this.taskList.size(); i++) {
                fw.write(String.format("%s\n", this.taskList.get(i).saveTask()));
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Something went wrong! Not stonks!\n");
        }
    }
}
