import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public String getInd(int i) {
        ArrayList<Task> arr = this.getList();
        return arr.get(i).toString();
    }

    String line = "------------------------------------------";

    public void addToDo(String input) { //when user keys in todo abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyToDoException();
            return;
        }
        Task t = new Todo(temp[1]);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        this.taskList.add(t);
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
//        DataHandler.saveData(this.taskList);
    }

    public void addToDo(Task input) { //when loading fr data [T][ ] abc
        this.taskList.add(input);
    }

    public void addDeadline(String input) { //when user keys in deadline abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyDeadlineException();
            return;
        }
        String data = temp[1];
        String description = data.split(" /by ", 2)[0];
        String by = data.split(" /by ", 2)[1];
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        Task t = new Deadline(description, dateTime);
        this.taskList.add(t);
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
//        DataHandler.saveData(this.taskList);
    }

    public void addDeadline(Task input) { //when loading fr data [D][ ] abc
        this.taskList.add(input);
    }

    public void addEvent(String input) { ////when user keys in event abc
        String[] temp = input.split(" ", 2);
        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyEventException();
            return;
        }
        String data = temp[1];
        String description = data.split(" /at ", 2)[0];
        String at = data.split(" /at ", 2)[1];
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
        Task t = new Event(description, dateTime);
        this.taskList.add(t);
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
    }

    public void addEvent(Task input) { //when loading fr data [E][ ] abc
        this.taskList.add(input);
    }

    public void markDone(String input) {
        System.out.println(line);
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        System.out.println("Nice! I've marked this task as done:");
        this.taskList.get(index).setDone();
        System.out.println(this.taskList.get(index));
        System.out.println(line);
//        DataHandler.saveData(this.taskList);
    }

    public void delete(String input) {
        String[] temp = input.split(" ", 2);

        try {
            Task t = new Todo(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            new EmptyDeleteException();
            return;
        }

        try {
            this.taskList.get(Integer.parseInt(temp[1]) - 1);
        } catch (NumberFormatException e) {
            new AlphabetsInsteadOfNumberException();
            return;
        }

        try {
            this.taskList.get(Integer.parseInt(temp[1]) - 1);
        } catch (IndexOutOfBoundsException e) {
            new EmptyListDeletionException();
            return;
        }

        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        int index = Integer.parseInt(temp[1]) - 1;
        System.out.println(this.taskList.get(index));
        this.taskList.remove(index);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println(line);
    }
    public void list() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            System.out.println(i + "." + this.taskList.get(i - 1));
        }
        System.out.println(line);
    }
}
