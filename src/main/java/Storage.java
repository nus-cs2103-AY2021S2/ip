import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String taskFilePath;
    protected String contactFilePath;

    public Storage(String taskFilePath, String contactFilePath) {
        this.taskFilePath = taskFilePath;
        this.contactFilePath = contactFilePath;

    }

    /**
     * Creates a new file to store tasks is the file does not exit yet.
     */
    public void taskRecorder() {
        File taskHistory = new File(taskFilePath);
        if (!taskHistory.exists()) {
            try {
                taskHistory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void contactRecorder() {
        File contactHistory = new File(contactFilePath);
        if (!contactHistory.exists()) {
            try {
                contactHistory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints the tasks stored from last usage of Duke.
     */
    public String taskHistory() {
        try {
            File history = new File(taskFilePath);
            Scanner s = new Scanner(history);
            String myTasks = "\nyour current tasks:\n";
            while (s.hasNext()) {
                String str = s.nextLine();
                myTasks += str + "\n";
            }
            s.close();
            if (myTasks == "\nyour current tasks:\n") {
                return "";
            } else {
                return myTasks;
            }
        } catch (FileNotFoundException e) {
            return "user history is empty";
        }
    }

    public String contactHistory() {
        try {
            File history = new File(contactFilePath);
            Scanner s = new Scanner(history);
            String myContacts = "\nyour current contacts:\n";
            while (s.hasNext()) {
                String str = s.nextLine();
                myContacts += str + "\n";
            }
            s.close();
            if (myContacts == "\nyour current contacts:\n") {
                return "";
            } else {
                return myContacts;
            }
        } catch (FileNotFoundException e) {
            return "user history is empty";
        }
    }

    /**
     * Stores data of tasks as user history.
     *
     * @param taskList that stores tasks.
     */
    public void recordTasks(ArrayList<Task> taskList) {
        try {
            new FileWriter(taskFilePath, false).close();
            int i = 1;
            FileWriter fw = new FileWriter(taskFilePath);
            for (Task t : taskList) {
                fw.write(i + ". "
                        + t.toString() + System.lineSeparator());
                i++;
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recordContacts(ArrayList<Contact> contactList) {
        try {
            new FileWriter(contactFilePath, false).close();
            int i = 1;
            FileWriter fw = new FileWriter(contactFilePath);
            for (Contact c : contactList) {
                fw.write(c.toString() + System.lineSeparator());
                i++;
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses user history duke.txt and loads taskList when Duke starts up.
     *
     * @param taskList that stores tasks.
     */
    public void loadTask(ArrayList<Task> taskList) {
        try {
            File history = new File(taskFilePath);
            Scanner s = new Scanner(history);
            while (s.hasNext()) {
                String task = s.nextLine();
                if (task.contains("[T]")) {
                    Todo myTask = Todo.parseTodo(task);
                    taskList.add(myTask);
                } else if (task.contains("[D]")) {
                    Deadline myTask = Deadline.parseDeadline(task);
                    taskList.add(myTask);
                } else if (task.contains("[E]")) {
                    Event myTask = Event.parseEvent(task);
                    taskList.add(myTask);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadContact(ArrayList<Contact> contactList) {
        try {
            File history = new File(contactFilePath);
            Scanner s = new Scanner(history);
            while (s.hasNext()) {
                String contact = s.nextLine();
                String[] contactSeg = contact.split(" ");
                String name = contactSeg[0].replace(":", "");
                String number = contactSeg[1];
                contactList.add(new Contact(name, number));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
