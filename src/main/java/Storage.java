import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {

    /**
     * Default path to store the user tasks.
     */
    public static final String DEFAULT_TASKS_STORAGE_FILEPATH = "tasks.txt";
    /**
     * Default path to store the user contacts.
     */
    public static final String DEFAULT_CONTACTS_STORAGE_FILEPATH = "contacts.txt";

    private TaskList tasks;
    private ContactList contacts;

    /**
     * Constructor for Storage.
     */
    public Storage() {
        tasks = new TaskList();
        contacts = new ContactList();
    }

    /**
     * Read file if it exist, else will create a new file with the name.
     *
     * @throws IOException Throw IO exception.
     */
    public void readOrCreateFile() throws IOException {
        File tasksObj = new File(DEFAULT_TASKS_STORAGE_FILEPATH);
        File contactsObj = new File(DEFAULT_CONTACTS_STORAGE_FILEPATH);

        if (tasksObj.exists()) {
            readTaskFileIntoList(tasks);
        } else {
            //noinspection ResultOfMethodCallIgnored
            tasksObj.createNewFile();
        }

        if (contactsObj.exists()) {
            readContactsFileIntoList(contacts);
        } else {
            //noinspection ResultOfMethodCallIgnored
            contactsObj.createNewFile();
        }
    }

    /**
     * Read the existing contacts file and create the list of contacts when the program is run.
     *
     * @param contacts The Contact Arraylist containing user contacts in sequence.
     */
    private void readContactsFileIntoList(ContactList contacts) {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(Storage.DEFAULT_CONTACTS_STORAGE_FILEPATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String object : lines) {
            String[] fullDetails = object.split(":");
            String name = fullDetails[0];
            String number = fullDetails[1].substring(1);
            contacts.add(new Contact(name, number));
        }
    }

    /**
     * Read the existing task file and create the list of tasks when the program is run.
     *
     * @param tasks The Task Arraylist containing user tasks in sequence.
     */
    private void readTaskFileIntoList(TaskList tasks) {
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(Storage.DEFAULT_TASKS_STORAGE_FILEPATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String object : lines) {
            if (object.charAt(1) == 'T') {
                if (object.charAt(4) == '-') {
                    tasks.add(new Todo(object.substring(7), true));
                } else {
                    tasks.add(new Todo(object.substring(7), false));
                }
            } else {
                String dateInfo = object.substring(object.indexOf("(") + 5, object.indexOf(")"));
                if (object.charAt(1) == 'D') {
                    if (object.charAt(4) == '-') {
                        tasks.add(new Deadline(object.substring(7, object.indexOf("(") - 1), dateInfo, true, true));
                    } else {
                        tasks.add(new Deadline(object.substring(7, object.indexOf("(") - 1), dateInfo, false, true));
                    }
                } else if (object.charAt(1) == 'E') {
                    if (object.charAt(4) == '-') {
                        tasks.add(new Event(object.substring(7, object.indexOf("(") - 1), dateInfo, true, true));
                    } else {
                        tasks.add(new Event(object.substring(7, object.indexOf("(") - 1), dateInfo, false, true));
                    }
                }
            }
        }
    }

    /**
     * Update the tasks into the file after each user input.
     *
     * @throws FileNotFoundException Throw exception if file does not exist, should not happen.
     */
    public void writeTaskListIntoFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(DEFAULT_TASKS_STORAGE_FILEPATH);
        ArrayList<Task> items = tasks.getTaskList();
        for (Task item : items) {
            writer.println(item.toString());
        }
        writer.close();
    }

    /**
     * Update the contacts into the file after each user input.
     *
     * @throws FileNotFoundException Throw exception if file does not exist, should not happen.
     */
    public void writeContactListIntoFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(DEFAULT_CONTACTS_STORAGE_FILEPATH);
        ArrayList<Contact> items = contacts.getContactList();
        for (Contact item : items) {
            writer.println(item.toString());
        }
        writer.close();
    }

    public TaskList getTaskList() {
        return this.tasks;
    }

    public ContactList getContactList() {
        return this.contacts;
    }
}
