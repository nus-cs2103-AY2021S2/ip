import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> list;

    public TaskManager() {
        list = new ArrayList<>();
    }

    public void takeEvent(String input, ArrayList<Task> list) {
        this.list = list;
        CheckErrors e = new CheckErrors(input, list);

        if (input.equals("list")) {
            listEvents();
            return;
        } else if (e.check()) {
//            System.out.println("pass check");
            if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                addNewTask(input);
            }
        }

    }

    public void markDone(String input) {
        Task task = list.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Good job! You got " + task.description
                + " done!\n" + Duke.line);
    }

    public void addNewTask(String input) {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new TodoTask(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] inputSplit = input.split("/");
            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
                    inputSplit[1].substring(3));
        } else {
            String[] inputSplit = input.split("/");
            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
                    inputSplit[1].substring(3));
        }
        list.add(newTask);
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Added: " + newTask.toString() + "\n" + Duke.line);
    }

    public void deleteTask(String input) {
//        Task task = list.get(Character.getNumericValue(input.charAt(5)) - 1);
//        task.markAsDone();
//        System.out.println("Good job! You got " + task.description + " done!");
        Task task = list.get(Integer.parseInt(input.substring(7)) - 1);
        list.remove(Integer.parseInt(input.substring(7)) - 1);
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Deleted: " + task.toString() + "\n" + Duke.line);
    }

    public void listEvents() {
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Here is a list of your tasks:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + (char) 9 + (char) 9 + (char) 9 + (i + 1) + ". " + list.get(i).toString());
        }
        System.out.println(Duke.line);
    }
}
