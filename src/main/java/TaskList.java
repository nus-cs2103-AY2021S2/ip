import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that encapsulate, control, and evaluate a list of task.
 */
public class TaskList {
    private List<Task> tasks;
    private History<List<Task>> histories;

    /**
     * Constructor of TaskList object
     */
    TaskList() {
        this.tasks = new ArrayList<>();
        this.histories = new History<>();
    }

    /**
     * Constructor of TaskList object
     * @param tasks List of Task
     */
    TaskList(List<Task> tasks) {
        this.tasks = tasks.stream().collect(Collectors.toList()); //deep-copy
        this.histories = new History<>();
    }

    /**
     * Iterate Task object in list and ask Ui to print them
     */
    public String iterateList() {
        if (this.tasks.size() == 0) {
            return Ui.showMessage("There are no task in your list");
        }
        String output = Ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            output += Ui.showMessage((i + 1) + "." + this.tasks.get(i));
        }
        return output;
    }

    /**
     * Set a task to be done and ask Ui to print the completion
     * @param input The String representation of the index of the task
     * @throws DukeException Index out of bound
     */
    public String finishATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            this.histories.add(this.tasks.stream().collect(Collectors.toList()));
            this.tasks.set(index, this.tasks.get(index).finishTask());
            return Ui.finishTaskMessage(this.tasks.get(index).toString());
        }
        catch(NumberFormatException e) {
            throw new DukeException("OOPS!!! The index must be an integer");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of done cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of done exceed the tasks' list.");
        }
    }

    /**
     * Delete a task and ask Ui to print the completion
     * @param input The String representation of the index of the task
     * @throws DukeException Index out of bound
     */
    public String deleteATask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input) - 1;
            Task deleted = this.tasks.get(index);
            this.histories.add(this.tasks.stream().collect(Collectors.toList()));
            this.tasks.remove(index);
            return Ui.deleteTaskMessage(deleted.toString(), this.tasks.size());
        }
        catch(NumberFormatException e) {
            throw new DukeException("OOPS!!! The index must be an integer");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of delete cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of delete exceed the tasks' list.");
        }
    }

    /**
     * Add task to the current TaskList and ask Ui to print the completion
     * @param task Task that is going to be added
     */
    public String addTask(Task task) throws DukeException {
        if (this.tasks.stream().filter(item -> item.equals(task)).collect(Collectors.toList()).size() != 0) {
            throw new DukeException("Same task has been added before !!!"); //duplicated items are not allowed
        }
        this.histories.add(this.tasks.stream().collect(Collectors.toList()));
        this.tasks.add(task);
        return Ui.addTaskMessage(task.toString(), this.tasks.size());
    }

    /**
     * Finding Tasks that match the keyword and ask Ui to print them
     * @param keyword Keyword to find in list of Tasks
     */
    public String findTasks(String keyword) {
        List<Task> temp;
        Predicate<Task> findKeywordFromTask = task -> task.toString().contains(keyword);
        temp = this.tasks.stream().filter(findKeywordFromTask).collect(Collectors.toList());
        if (temp.size() == 0) {
            return Ui.showMessage("No matching task");
        }
        String output = Ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < temp.size(); i++) {
            output += Ui.showMessage(String.valueOf(i + 1) + "." + temp.get(i));
        }
        return output;

    }

    public String undo(String description) throws DukeException{
        if (this.histories.isEmpty()) {
            throw new DukeException("Sorry, the history of TaskList is empty!!!");
        }
        if (description.equals("") || description.equals("1")) {
            this.tasks = this.histories.getLast();
            return "Successfully undo to the previous state!";
        }
        int numberOfUndo;
        try {
            numberOfUndo = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry!!! Undo takes an integer as an argument");
        }
        int totalReset = this.histories.compareNumberOfHistoriesWithInput(numberOfUndo);
        this.tasks = this.histories.getMultipleItems(numberOfUndo);
        return totalReset >= numberOfUndo
                ? "Successfully undo " + totalReset + " previous states!"
                : "only Successfully undo " + totalReset + " previous states!";

    }

    public String sort() throws DukeException{
        if (this.tasks.isEmpty()) {
            throw new DukeException("Sorry, the TaskList is empty!!!");
        }
        this.histories.add(this.tasks.stream().collect(Collectors.toList()));
        this.tasks.sort(Comparator.comparing(a -> a.task));
        return "TaskList successfully sorted alphabetically";
    }

    public String statistics() throws DukeException {
        if (this.tasks.isEmpty()) {
            throw new DukeException("Sorry, the TaskList is empty");
        }
        int doneTasks = this.tasks.stream().filter(task -> task.hasDone()).
                collect(Collectors.toList()).size();
        return "Task done: " + doneTasks * 100 / this.tasks.size() + "% from " + this.tasks.size();
    }

    public String getContact(String description) throws DukeException {
        try {
            int index = Integer.parseInt(description) - 1;
            return Ui.showMessage("Task " + description +": " + this.tasks.get(index).getContactDetails());
        }
        catch(NumberFormatException e) {
            throw new DukeException("The command 'contact' must have first argument an index");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of contact cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of contact exceed the tasks' list.");
        }
    }

    public String addContactDetails(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("No contact details given!!");
        }
        String[] inputs = description.split(" ");
        int index;
        try {
            index = Integer.parseInt(inputs[0]) - 1;
            this.histories.add(this.tasks.stream().collect(Collectors.toList()));
            this.tasks.get(index).addContactDetails(inputs[1], inputs[2]);
            return Ui.showMessage("Succesfully add contacts to task number " + (index + 1));
        }
        catch(NumberFormatException e) {
            throw new DukeException("contact must have first argument an index");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The arguments of addContact cannot be empty.");
        }
        catch(IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The index of addContact exceed the tasks' list.");
        }
    }

    public String getAllContactDetails() throws DukeException {
        if (this.tasks.isEmpty()) {
            throw new DukeException("Sorry, the TaskList is empty");
        }
        if (this.tasks.stream().filter(task -> task.hasContactDetails())
                .collect(Collectors.toList()).isEmpty()) {
            return Ui.showMessage("No contact details for all tasks");
        }
        String output = Ui.showMessage("Here are the contact details available in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).hasContactDetails()) {
                output += Ui.showMessage("Task " + (i + 1) + ": " + this.tasks.get(i).getContactDetails());
            }
        }
        return output;
    }

    /**
     * Get List of Task
     * @return List of Task
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}

