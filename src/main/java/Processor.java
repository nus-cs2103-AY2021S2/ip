import java.util.ArrayList;

public class Processor {
    ArrayList<Task> tasks;
    Database dukeDataBase;

    Processor(ArrayList<Task> taskList, Database database) {
        tasks = taskList;
        dukeDataBase = database;
    }

    public boolean processSentence(String input) {
        boolean isOver = false;
        try {
            Command command = Parser.parseCommand(input);
            switch (command) {
            case LIST:
                Printer.listTasks(tasks);
                break;
            case DONE:
                markAsComplete(Parser.getDoneIndex(input));
                break;
            case TODO:
                addThisTask(Parser.getTodo(input));
                break;
            case DEADLINE:
                addThisTask(Parser.getDeadline(input));
                break;
            case EVENT:
                addThisTask(Parser.getEvent(input));
                break;
            case DELETE:
                removeTask(Parser.getDeleteIndex(input));
                break;
            case BYE:
                isOver = true;
                Printer.sayGoodBye();
                break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return isOver;
    }

    public void addThisTask(Task task) {
        System.out.println(" Added: ");
        tasks.add(task);
        dukeDataBase.updateInFile(tasks);
        System.out.println("  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks.");
    }

    public void markAsComplete(int index) throws TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task completedTask = tasks.get(index);
            completedTask.complete();
            dukeDataBase.updateInFile(tasks);
            System.out.println(" Marked. How cool is that?");
            System.out.println("  " + completedTask);
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }

    public void removeTask(int index) throws TaskIndexOutOfBoundException {
        if (index < tasks.size()) {
            Task removingTask = tasks.get(index);
            tasks.remove(index);
            dukeDataBase.updateInFile(tasks);
            System.out.println(" Following task is removed:");
            System.out.println("  " + removingTask);
            System.out.println(" Now you have " + tasks.size() + " tasks.");
        } else {
            throw new TaskIndexOutOfBoundException("There is no task numbered " + (index + 1) + "!");
        }
    }
}
