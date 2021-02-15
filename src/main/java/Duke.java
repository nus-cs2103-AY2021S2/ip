import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main Duke chatbot class.
 */
public class Duke implements IDuke{
    /** List of Tasks */
    private final ArrayList<ITask> list;

    private Duke(ArrayList<ITask> lst){

        this.list = new ArrayList<>(lst);

    }

    public static Duke getDuke(){
        return new Duke(new ArrayList<>());
    }

    @Override
    public void greeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    @Override
    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public IDuke processInput(String input) {
        try {
            if (input.equals("list")) {
               display();
                return this;
            } else if (input.matches("^done.*")) {
                // Handle done command
                if (!input.matches("^done -?[0-9]+$")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong done command! Format: done <taskId>");
                }
                int index = Integer.parseInt(input.split(" ", 2)[1]);
                return handleDone(index);
            } else if (input.matches("^todo.*")) {
                // Handle todo command
                if (!input.matches("^todo .*")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong todo command! Format: todo <taskName>");
                }
                String param = input.split(" ", 2)[1];
                return handleToDo(param);
            } else if (input.matches("^deadline.*")) {
                // Handle deadline command
                if (!input.matches("^deadline .* /by .*")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong deadline command!\nFormat: deadline <taskName> /by <time>");
                }
                String[] params = input.split(" ", 2)[1].split(" /by ");
                return handleDeadline(params[0], params[1]);
            } else if (input.matches("^event.*")) {
                // Handle event command
                if (!input.matches("^event .* /at .*")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong event command!\nFormat: event <taskName> /at <time>");
                }
                String[] params = input.split(" ", 2)[1].split(" /at ");
                return handleEvent(params[0], params[1]);
            } else if (input.matches("^delete.*")) {
                // Handle delete command
                if (!input.matches("^delete -?[0-9]+$")) {
                    throw new DukeIllegalArgumentException(
                            "Wrong delete command! Format: delete <taskId>");
                }
                int index = Integer.parseInt(input.split(" ")[1]);
                return handleDelete(index);
            } else {
                throw new DukeUnrecognizedArgumentException("Unrecognizable command!");
            }
        } catch (DukeIllegalArgumentException e) {
            System.out.println(e);
        } catch (DukeUnrecognizedArgumentException e) {
            System.out.println(" Sorry I don't understand your command");
        } catch (Exception e) {
            System.out.println(e);
        }

        return this;

    }

    @Override
    public IDuke addTask(ITask task) {
        ArrayList<ITask> newList = new ArrayList<>(this.list);
        newList.add(task);
        return new Duke(newList);
    }

    @Override
    public ITask getTask(int id) {
        if (id - 1 > list.size() || id < 0) {
            throw new IllegalArgumentException("Task id out of bound!");
        }
        return list.get(id - 1);
    }


//    public void add(String input) throws DukeEmptyCommandException{
//        if(input.equals("todo")){
//            throw new DukeEmptyCommandException("OOPS!!! The description of a todo cannot be empty.");
//        } else if (input.equals("deadline")){
//            throw new DukeEmptyCommandException("OOPS!!! The description of a deadline cannot be empty.");
//        } else if (input.equals("event")){
//            throw new DukeEmptyCommandException("OOPS!!! The description of an event cannot be empty.");
//        } else {
//            Task t = new Task(input);
//            this.list.add(t);
//            System.out.println("Got it. I've added this task: ");
//            System.out.println(t.toString());
//
//        }
//    }
    @Override
    public void display(){
        System.out.println("Here are the tasks in your list:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(" ").append(i + 1).append(". ")
                    .append(list.get(i).toString()).append("\n");
        }
        System.out.println(sb.toString());
    }



    @Override
    public int numOfTasks() {
        return list.size();
    }



    private IDuke delete(int id) throws DukeIllegalArgumentException {
        if (id - 1 > list.size() || id < 0) {
            throw new DukeIllegalArgumentException(
                    "Cannot delete task! Task id out of bound!");
        }
        ArrayList<ITask> newList = new ArrayList<>(this.list);
        newList.remove(id - 1);
        return new Duke(newList);
    }


    private IDuke handleDelete(int index) {
        if (index < 1 || index > numOfTasks()) {
            throw new DukeIllegalArgumentException("Task index out of bound!");
        }
        IDuke newDuke = delete(index);
        System.out.println(
                " I've removed this task:\n\t" + getTask(index)
                        + "\n Now you have " + newDuke.numOfTasks() + " task(s) in the list.");
        return newDuke;
    }


    public IDuke done(int index){

            if (index - 1 > list.size() || index < 0) {
                throw new DukeIllegalArgumentException(
                        "Cannot done task! Task id out of bound!");
            } else if (list.get(index - 1).isDone()) {
                throw new DukeIllegalArgumentException(
                        "Cannot done task! Task is already done!");
            }
            ArrayList<ITask> newList = new ArrayList<>(this.list);
            newList.set(index - 1, newList.get(index - 1).markDone());
            return new Duke(newList);
        }


    private IDuke handleDone(int index) throws DukeIllegalArgumentException {
        if (index < 1 || index > numOfTasks()) {
            throw new DukeIllegalArgumentException("Task index out of bound!");
        }
        IDuke newDuke = done(index);
        System.out.println(
                "Nice! I've marked this task done!\n" + newDuke.getTask(index));
        return newDuke;
    }


    private IDuke handleToDo(String description) throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of todo cannot be empty!");
        }
        ITask task = Todo.getTodo(description);
        IDuke newDuke = addTask(task);
        System.out.println(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have "
                        +  newDuke.numOfTasks() + " task(s) in the list.");
        return newDuke;
    }


    private IDuke handleDeadline(String description, String by)
            throws DukeIllegalArgumentException{
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of deadline cannot be empty!");
        }
        if (by.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of deadline cannot be empty!");
        }
        ITask task = Deadline.getDeadline(description, by);
        IDuke newDuke = addTask(task);
        System.out.println("Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.numOfTasks()
                        + " task(s) in the list.");
        return newDuke;
    }

    private IDuke handleEvent(String description, String at)
            throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of event cannot be empty!");
        }
        if (at.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of event cannot be empty!");
        }
        ITask task = Event.getEvent(description, at);
        IDuke newDuke = addTask(task);
        System.out.println(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.numOfTasks()
                        + " task(s) in the list.");
        return newDuke;
    }


    public static void main(String[] args) {

        IDuke duke = getDuke();
        Scanner sc = new Scanner(System.in);

        // Greet user
        duke.greeting();

        // Handle commands until user types bye
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (!s.matches("\\s*")) {
                // Ignore white spaces or empty lines
                duke = duke.processInput(s);
            }
        }

        // Exit duke
        duke.bye();

    }
}
