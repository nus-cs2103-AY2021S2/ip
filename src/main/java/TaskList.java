import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void populate(ListParser p) {
        String typeOfTask = p.getTypeOfTask();
        Boolean isDone = p.getIsDone();
        String description = p.getDescription();
        LocalDateTime time = p.getTime();

        switch(typeOfTask) {
        case "T":
            tasks.add(new Todo(description, isDone));
            break;
        case "D":
            tasks.add(new Deadline(description, isDone, time));
            break;
        case "E":
            tasks.add(new Event(description, isDone, time));
            break;
        }
    }

    public void add(Parser p) throws EmptyDescription {
        String typeOfTask = p.getTypeOfTask();
        String description = p.getDescription();
        if (description.equals("")) {
            throw new EmptyDescription(typeOfTask);
        } else {
            LocalDateTime time = p.getTime();
            Task newTask;
            if (typeOfTask.equals("todo")) {
                newTask = new Todo(description);
            } else if (typeOfTask.equals("deadline")) {
                newTask = new Deadline(description, time);
            } else {
                newTask = new Event(description, time);
            }
            tasks.add(newTask);
            String instructions = Response.ADD.toString() + newTask + "\n" + this.status();
            enclose(instructions);
        }
    }

    public void delete(Parser p) {
        int i = Integer.parseInt(p.getDescription()) - 1;
        Task task = tasks.get(i);
        tasks.remove(i);

        String instructions = Response.DELETE.toString() + task + "\n" + this.status();
        enclose(instructions);
    }

    public void markAsDone(Parser p) throws EmptyDescription {
        if (p.getDescription().equals("")) {
            throw new EmptyDescription(p.getTypeOfTask());
        }
        int i = Integer.parseInt(p.getDescription()) - 1;
        tasks.set(i, tasks.get(i).setDone());
        enclose(Response.DONE.toString() + tasks.get(i) + "\n");
    }

    public void list() {
        String msg = "";
        for(int i = 0; i < tasks.size(); i++) {
            msg += (i + 1) + "." + tasks.get(i) + "\n";
        }
        enclose(Response.LIST.toString() + msg);
    }

    /**
     * Prints output to user in generic format.
     */
    public void enclose(String reply) {
        System.out.println("---------------------------------------");
        System.out.println(reply);
        System.out.println("---------------------------------------\n");
    }

    public  String status() {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }
}
