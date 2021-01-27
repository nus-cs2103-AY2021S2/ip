import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Parser p) throws EmptyDescription {
        String typeOfTask = p.getTypeOfTask();
        String description = p.getDescription();
        if (description.equals("")) {
            throw new EmptyDescription(typeOfTask);
        } else {
            String time = p.getTime();
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
            Duke.output(instructions);
        }
    }

    public void delete(Parser p) {
        int i = Integer.parseInt(p.getDescription()) - 1;
        Task task = tasks.get(i);
        tasks.remove(i);

        String instructions = Response.DELETE.toString() + task + "\n" + this.status();
        Duke.output(instructions);
    }

    public void markAsDone(Parser p) throws EmptyDescription {
        if (p.getDescription().equals("")) {
            throw new EmptyDescription(p.getTypeOfTask());
        }
        int i = Integer.parseInt(p.getDescription()) - 1;
        tasks.set(i, tasks.get(i).setDone());
        Duke.output(Response.DONE.toString() + tasks.get(i) + "\n");
    }

    public void list() {
        String msg = "";
        for(int i = 0; i < tasks.size(); i++) {
            msg += (i + 1) + "." + tasks.get(i) + "\n";
        }
        Duke.output(Response.LIST.toString() + msg);
    }

    public  String status() {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }
}
