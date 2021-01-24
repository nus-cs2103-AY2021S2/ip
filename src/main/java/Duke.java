import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Duke {
    ArrayList<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void storeTask(Task task) {
        taskList.add(task);
        System.out.println("Duchess: Great! I have added:" + "\n" + task + "\n" + "U have " + taskList.size() + " tasks in the list now :)");
    }

    public void printList() {
        String msg = "Here are the tasks in your list:";
        for(int i = 0; i < taskList.size(); i++) {
            msg+= "\n" +  (i + 1) + ". " + taskList.get(i);
        }
        System.out.println(msg);
    }

    public void markComplete(int n) {
        Task temp = this.taskList.get(n - 1);
        temp.checkTask();
        System.out.println("Duchess: Woohoo I've checked off this task:" + "\n" + temp);

    }

    public void deleteTask(int task) {
        Task temp = this.taskList.get(task - 1);
        this.taskList.remove(task - 1);
        System.out.println("Duchess: As requested, i have removed this task:" + "\n" + temp + "\n" + "U have " + taskList.size() + " tasks in the list now :)");
    }

    public String taskListToString(ArrayList<Task> taskList) {
        String res = "";
        for(Task task : taskList) {
            char cat = task.getCat();
            String name = task.getName();
            int checked;
            if (task.getCompleted()) {
                checked = 1;
            } else {
                checked = 0;
            }
            if (cat == 'E') {
                Event event = (Event) task;
                String date = event.getFormattedTime();
                res += cat + " : " + checked + " : " + name + " : " + date + "\n";
            } else if (cat == 'D') {
                Deadline deadline = (Deadline) task;
                String date = deadline.getFormattedDeadline();
                res += cat + " : " + checked + " : " + name + " : " + date + "\n";
            } else {
                res += cat + " : " + checked + " : " + name + "\n";
            }
        }
        return res;
    }

    public void fileToList(File dataFile) {
        try {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String arr[] = data.split(" : ", 4);
                String cat = arr[0];
                String completed = arr[1];
                String des = arr[2];
                if (cat.equals("E")) {
                    String date = arr[3];
                    Event event = new Event(des, date);
                    if (completed == "1") {
                        event.checkTask();
                    }
                    this.taskList.add(event);
                } else if (cat.equals("D")) {
                    String date = arr[3];
                    Deadline deadline = new Deadline(des, date);
                    if (completed.equals("1")) {
                        deadline.checkTask();
                    }
                    this.taskList.add(deadline);
                } else {
                    Todo todo = new Todo(des);
                    if (completed.equals("1")) {
                        todo.checkTask();
                    }
                    this.taskList.add(todo);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        // Create a Scanner object
        Scanner sc = new Scanner(System.in);
        File dataFile = new File("src/data/duke.txt");
        try {
            if (!dataFile.exists()) {
                File parent = dataFile.getParentFile();
                parent.mkdir();
                dataFile.createNewFile();
            }
            chatbot.fileToList(dataFile);
            FileWriter writer = new FileWriter(dataFile);
            System.out.println("Duchess: Hello, Duchess here. What can i do for you?");
            boolean readOn = true;
            while (readOn) {
                // Read user input
                String command = sc.nextLine();
                String arr[] = command.split(" ", 2);
                String action = arr[0];
                switch (action) {
                    case "bye":
                        readOn = false;
                        break;
                    case "list":
                        chatbot.printList();
                        break;
                    case "done":
                        if (arr.length <= 1) {
                            throw new MissingTaskException();
                        }
                        int n = Integer.parseInt(arr[1]);
                        chatbot.markComplete(n);
                        break;
                    case "todo":
                        if (arr.length <= 1) {
                            throw new MissingInputException(action);
                        }
                        Task todo = new Todo(arr[1]);
                        chatbot.storeTask(todo);
                        break;
                    case "event":
                        if (arr.length <= 1) {
                            throw new MissingInputException(action);
                        }
                        System.out.println("Duchess: When will this event be?");
                        String date = sc.nextLine();
                        Task event = new Event(arr[1], date);
                        chatbot.storeTask(event);
                        break;
                    case "deadline":
                        if (arr.length <= 1) {
                            throw new MissingInputException(action);
                        }
                        System.out.println("Duchess: When does this have to be done by?");
                        String due = sc.nextLine();
                        Task deadline = new Deadline(arr[1], due);
                        chatbot.storeTask(deadline);
                        break;
                    case "delete":
                        if (arr.length <= 1) {
                            throw new MissingTaskException();
                        }
                        chatbot.deleteTask(Integer.parseInt(arr[1]));
                        break;
                    default:
                        throw new UnlcearInputException();
                }
            }
            writer.write(chatbot.taskListToString(chatbot.taskList));
            writer.close();
        } catch (MissingInputException e) {
            System.out.println((e.getMessage()));
        } catch (UnlcearInputException e) {
            System.out.println((e.getMessage()));
        } catch (DukeExceptions e) {
            System.out.println((e.getMessage()));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
        System.out.println("Duchess: Bye, Have an awesome day!");
        sc.close();
    }
}

