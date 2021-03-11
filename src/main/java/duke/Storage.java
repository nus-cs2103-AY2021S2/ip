package duke;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String fileName;

    public Storage(String fileName)  {
        this.filePath = Duke.getPath();
        this.fileName = fileName;
    }

    /**
     * Writes the tasks in the task list into the specified file.
     *
     * @param tasks Task list to be saved.
     */
    public void saveTaskList(String tasks) {
        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("This file cannot be saved... much like you!");
        }
    }

    /**
     * Reads the saved task list in the file and adds the task into the task list.
     *
     * @return Task list with tasks from the file loaded.
     */
    public TaskList loadTaskList() {
        try {
            TaskList taskList = new TaskList();
            File f = new File(filePath + fileName);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskArr = taskString.split("\\| ");
                Task task = null;
                switch(taskArr[0]) {
                case "T ":
                    String input = taskArr[2];
                    task = new Todo(input);
                    break;
                case "D ":
                    LocalDateTime dateTime = LocalDateTime.parse(taskArr[3].substring(4),
                            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
                    task = new Deadline(dateTime, taskArr[2].substring(0, taskArr[2].length() - 1));
                    break;
                case "E ":
                    LocalDateTime dateTimeStart = LocalDateTime.parse(taskArr[3].substring(6, taskArr[3].length() - 1),
                            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
                    LocalDateTime dateTimeEnd = LocalDateTime.parse(taskArr[4].substring(4),
                            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"));
                    task = new Event(dateTimeStart, dateTimeEnd, taskArr[2].substring(0, taskArr[2].length() - 1));
                    break;
                }
                if (task != null && taskArr[1].equals("X ")) {
                    task.checkTask();
                }
                taskList.addTask(task);
            }
            return taskList;
        } catch (IOException e) {
            System.out.println("This file cannot be loaded... My apologies");
            return new TaskList();
        }
    }

    /**
     * Writes the contacts in the contact list into the specified file.
     *
     * @param contacts Contact list to be saved.
     */
    public void saveContactList(String contacts) {
        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(contacts);
            fw.close();
        } catch (IOException e) {
            System.out.println("This file cannot be saved... much like you!");
        }
    }

    /**
     * Reads the saved contact list in the file and adds the contacts into the contact list.
     *
     * @return Contact list with contacts from the file loaded.
     */
    public ContactList loadContactList() {
        try {
            File f = new File(filePath + fileName);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            ContactList contactList = new ContactList();
            while (sc.hasNext()) {
                String contactString = sc.nextLine();
                String[] details = contactString.split("/name ");
                String[] nameInfo = details[1].split(" /number ");
                String[] numInfo = nameInfo[1].split(" /address ");
                String name = nameInfo[0];
                String numString = numInfo[0];
                int number = Integer.valueOf(numString);
                String address = "";
                if (numInfo.length == 2) {
                    address = numInfo[1];
                }
                Contact contact = new Contact(name, number, address);
                contactList.addContact(contact);
            }
            return contactList;
        } catch (IOException e) {
            return new ContactList();
        }
    }
}
