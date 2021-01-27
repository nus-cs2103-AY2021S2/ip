import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    private static final String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            File savedTasksFile = new File("data/duke.txt");
            if (!savedTasksFile.exists()) {
                File parentDir = savedTasksFile.getParentFile();
                parentDir.mkdir();
                savedTasksFile.createNewFile();
            }
            loadTasks(savedTasksFile);
            FileWriter fw = new FileWriter(savedTasksFile);

            System.out.println("____________________________________________________________\n" +
                    "Hello! I'm Duke\n" +
                    "How can I assist you?\n" +
                    "____________________________________________________________");
            while (true) {
                String text = sc.nextLine();
                String[] words = text.split(" ");
                if (text.equals("bye")) {
                    System.out.println("Bye. Till next time!");
                    System.out.println(horizontalLine);
                    break;
                } else if (text.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int num = 1;
                    for (Task task : list) {
                        System.out.println(String.format("%d. %s", num, task));
                        num++;
                    }
                    System.out.println(horizontalLine);
                } else if (words[0].equals("done")) {
                    int num = Integer.parseInt(words[1]);
                    Task task = list.get(num - 1);
                    task.done();
                    System.out.println("Nice! I've marked this task as done:\n" + task);
                    System.out.println(horizontalLine);
                } else if (words[0].equals("delete")) {
                    int num = Integer.parseInt(words[1]);
                    deleteAndPrintTask(num);
                } else if (words[0].equals("todo")) {
                    try {
                        emptyDesc(words);
                        Task todo = new Todo(text.substring(text.indexOf(" ") + 1));
                        addAndPrintTask(todo);
                    } catch (DukeException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.\n" + horizontalLine);
                    }
                } else if (words[0].equals("deadline")) {
                    try {
                        emptyDesc(words);
                        Task deadline = new Deadline(text.substring(text.indexOf(" ") + 1, text.indexOf("/") - 1), text.substring(text.indexOf("/") + 4));
                        addAndPrintTask(deadline);
                    } catch (DukeException e) {
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.\n" + horizontalLine);
                    }
                } else if (words[0].equals("event")) {
                    try {
                        emptyDesc(words);
                        Task event = new Event(text.substring(text.indexOf(" ") + 1, text.indexOf("/") - 1), text.substring(text.indexOf("/") + 4));
                        addAndPrintTask(event);
                    } catch (DukeException e) {
                        System.out.println("OOPS!!! The description of an event cannot be empty.\n" + horizontalLine);
                    }
                } else {
                    try {
                        throw new DukeException();
                    } catch (DukeException e) {
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + horizontalLine);
                    }
                }
            }
            fw.write(saveTasks(list));
            fw.close();
        } catch (FileNotFoundException e){
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        sc.close();
    }

    private static void addAndPrintTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void emptyDesc(String[] words) throws DukeException {
        if (words.length == 1) {
            throw new DukeException();
        }
    }

    private static void deleteAndPrintTask(int num) {
        Task removedTask = list.remove(num-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static String saveTasks(ArrayList<Task> list) {
        String fileContent = "";
        for (Task task: list) {
            int num;
            String description = task.getDescription();
            char type = task.type();
            if (task.getDoneStatus() == false) {
                num = 0;
            }
            else {
                num = 1;
            }
            if (type == 'T') {
                fileContent += type + " ; " + num + " ; " + description + "\n";
            }
            else if (type == 'D') {
                Deadline deadline = (Deadline) task;
                fileContent += type + " ; " + num + " ; " + description +  " ; " + deadline.getDeadlineTime() + "\n";
            }
            else { //type 'E'
                Event event = (Event) task;
                fileContent += type + " ; " + num + " ; " + description +  " ; " + event.getEventTime() + "\n";
            }
        }
        return fileContent;
    }

    public static void loadTasks(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] taskArr = line.split(" ; ", 4);
            char type = taskArr[0].charAt(0);
            String num = taskArr[1];
            String description = taskArr[2];
            if (type == 'T') {
                Todo todo = new Todo(description);
                if (num.equals("1")) {
                    todo.done();
                }
                list.add(todo);
            } else if (type == 'D') {
                String when = taskArr[3];
                Deadline deadline = new Deadline(description, when);
                if (num.equals("1")) {
                    deadline.done();
                }
                list.add(deadline);
            } else { //type 'E'
                String when = taskArr[3];
                Event event = new Event(description, when);
                if (num.equals("1")) {
                    event.done();
                }
                list.add(event);
            }
        }
        s.close();
    }
}
