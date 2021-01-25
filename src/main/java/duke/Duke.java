package duke;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    static class FastIO extends PrintWriter
    {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static class Task{
        /** The task that needs to be done, as a String */
        protected String todo;
        /** Whether or not the task is complete */
        protected boolean done;

        public Task(String s) {
            this.todo = s;
            this.done = false;
        }

        public void setDone() {
            this.done = true;
        }

        public String getTodo() {
            return this.todo;
        }

        /**
         * Returns a String form of the current Task to be saved onto the hard disk.
         *
         * @return the Task as a String to be saved.
         */
        public String saveToData() {
            if (this.done) {
                return ("T | 1 | " + todo);
            } else {
                return ("T | 0 | " + todo);
            }
        }

        /**
         * Returns a nicely formatted String from the given LocalDateTime.
         *
         * @param date Date to be formatted.
         * @return inputted date as a String.
         */
        public String dateFormat(LocalDateTime date) {
            return(date.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")));
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[T][ ] " + todo);
            } else {
                return ("[T][X] " + todo);
            }
        }

    }

    public static class Deadline extends Task {
        /** The date/time that the Deadline should be done by */
        private LocalDateTime doneBy;

        public Deadline(String s, String doneBy) {
            super(s);
            String[] split = doneBy.split("\\s+");
            if (split.length == 1) {
                this.doneBy = LocalDate.parse(doneBy).atTime(0,0);
            } else if (split.length == 2) {
                this.doneBy = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
            } else {
                throw new DateTimeParseException("","",1);
            }

        }

        /**
         * Returns a String form of the current Deadline to be saved onto the hard disk.
         *
         * @return the Deadline as a String to be saved.
         */
        public String saveToData() {
            if (this.done) {
                return ("D | 1 | " + todo + " | " + doneBy.toString().replace("T", " "));
            } else {
                return ("D | 0 | " + todo + " | " + doneBy.toString().replace("T", " "));
            }
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[D][ ] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")");
            } else {
                return ("[D][X] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")");
            }
        }
    }

    public static class Event extends Task {
        /** The time at which the Event happens */
        private LocalDateTime time;

        public Event(String s, String time) throws DateTimeParseException {
            super(s);
            String[] split = time.split("\\s+");
            if (split.length == 1) {
                this.time = LocalDate.parse(time).atTime(0,0);
            } else if (split.length == 2) {
                this.time = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
            } else {
                throw new DateTimeParseException("","",1);
            }
        }

        /**
         * Returns a String form of the current Event to be saved onto the hard disk.
         *
         * @return the Event as a String to be saved.
         */
        public String saveToData() {
            if (this.done) {
                return ("E | 1 | " + todo + " | " + time.toString().replace("T", " "));
            } else {
                return ("E | 0 | " + todo + " | " + time.toString().replace("T", " "));
            }
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[E][ ] " + this.todo + " (at:" + dateFormat(this.time) + ")");
            } else {
                return ("[E][X] " + this.todo + " (at:" + dateFormat(this.time) + ")");
            }
        }
    }

    public class Storage {

        /** The path of the storage file where the TaskList is stored*/
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        /**
         * Loads a TaskList from storage, if it exists.
         * Else creates a new storage file.
         *
         * @return the TaskList stored on the hard disk.
         */
        public TaskList load() {
            File taskData = new File(filePath);

            TaskList leest = new TaskList();

            //opens the duke.txt file to read from, creates file if not found
            try {
                Scanner s = new Scanner(taskData);
                while (s.hasNext()) {
                    String[] line = s.nextLine().split(" \\| ");
                    switch (line[0]) {
                    case "T":
                        Task task = new Task(line[2]);
                        if (line[1].equals("1")) {
                            task.setDone();
                        }
                        leest.add(task);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(line[2], line[3]);
                        if (line[1].equals("1")) {
                            deadline.setDone();
                        }
                        leest.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(line[2], line[3]);
                        if (line[1].equals("1")) {
                            event.setDone();
                        }
                        leest.add(event);
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                try {
                    taskData.createNewFile();
                } catch (IOException f) {
                    try {
                        Path path = Paths.get("test/");
                        Files.createDirectories(path);
                        taskData.createNewFile();
                    } catch (IOException g) {
                        System.err.println("Failed to create directory uwu");
                    }
                }
            }
            return leest;
        }

        /**
         * Writes the task to a file, erasing any previous data.
         *
         * @param filePath The storage file to be written to.
         * @param textToAdd The data to be written.
         * @throws IOException If the file does not exist.
         */
        private void writeToFile(String filePath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd + "\n");
            fw.close();
        }

        /**
         * Appends the task to a file.
         *
         * @param filePath The storage file to be written to.
         * @param textToAdd The data to be written.
         * @throws IOException If the file does not exist.
         */
        private void appendToFile(String filePath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(textToAdd + "\n");
            fw.close();
        }

        /**
         * Saves the given TaskList to a file.
         *
         * @param l The TaskList to be saved.
         */
        public void saveAsFile(TaskList l) {
            boolean isFirst = true;
            if (l.isEmpty()) {
                try {
                    writeToFile(filePath, " ");
                } catch (IOException e) {
                    System.out.println("Something went wrong uwu: " + e.getMessage());
                }
            }
            for (Task t : l.getList()) {
                try {
                    if (isFirst) {
                        writeToFile(filePath, t.saveToData());
                        isFirst = false;
                    } else {
                        appendToFile(filePath, t.saveToData());
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong uwu: " + e.getMessage());
                }
            }
        }

    }

    public static class TaskList {

        private List<Task> leest;

        public TaskList() {
            this.leest = new ArrayList<>();
        }

        public void add(Task t) {
            this.leest.add(t);
        }

        public boolean isEmpty() {
            return this.leest.isEmpty();
        }

        public int size() {
            return this.leest.size();
        }

        public Task get(int i) {
            return this.leest.get(i);
        }

        public void remove(Task t) {
            this.leest.remove(t);
        }

        public List<Task> getList() {
            return this.leest;
        }

    }

    public class Parser {

        /**
         * Parses a given input and performs actions as necessary based on the input.
         * The main driver of the Duke class.
         *
         * @param leest The current TaskList.
         * @param input Input that is being read in.
         */
        public void parse(TaskList leest, String input) {

            String[] split = input.split("\\s+");

            switch (split[0]) {
            case "list":
                if (leest.size() == 0) {
                    ui.emptyListMessage();
                } else {
                    ui.showList(leest);
                }
                break;
            case "done":
                try {
                    int done = Integer.parseInt(split[1]) - 1;
                    leest.get(done).setDone();
                    ui.setDone(leest.get(done));
                    storage.saveAsFile(leest);
                } catch (Exception e) {
                    ui.errorMessage("invalidDone");
                }
                break;
            case "bye":
                ui.byeBye();
                return;
            case "todo":
                try {
                    leest.add(new Task(input.substring(5)));
                    ui.taskAdded(leest.get(leest.size() - 1));
                    ui.showTaskListSize(leest.size());
                    storage.saveAsFile(leest);
                } catch (Exception e) {
                    ui.errorMessage("invalidTodo");
                }
                break;
            case "deadline":
                try {
                    String[] splitagain = input.substring(9).split("/by");
                    leest.add(new Deadline(splitagain[0], splitagain[1].substring(1)));
                    ui.taskAdded(leest.get(leest.size() - 1));
                    ui.showTaskListSize(leest.size());
                    storage.saveAsFile(leest);
                } catch (DateTimeParseException de) {
                    ui.errorMessage("dateTimeError");
                } catch (Exception e) {
                    ui.errorMessage("invalidDeadline");
                }
                break;
            case "event":
                try {
                    String[] splitagain2 = input.substring(6).split("/at");
                    leest.add(new Event(splitagain2[0], splitagain2[1].substring(1)));
                    ui.taskAdded(leest.get(leest.size() - 1));
                    ui.showTaskListSize(leest.size());
                    storage.saveAsFile(leest);
                } catch (DateTimeParseException de) {
                    ui.errorMessage("dateTimeError");
                } catch (Exception e) {
                    //System.out.println(e);
                    ui.errorMessage("invalidEvent");
                }
                break;
            case "delete":
                try {
                    Task toDelete = leest.get(Integer.parseInt(split[1]) - 1);
                    ui.deleteTask(toDelete);
                    leest.remove(toDelete);
                    ui.showTaskListSize(leest.size());
                    storage.saveAsFile(leest);
                } catch (Exception e) {
                    ui.errorMessage("invalidDelete");
                }
                break;
            case "find":
                String toFind = input.substring(5);
                TaskList toReturn = new TaskList();
                for (Task t : leest.getList()) {
                    if (t.getTodo().contains(toFind)) {
                        toReturn.add(t);
                    }
                }
                ui.showSearchList(toReturn);
                break;
            default:
                ui.errorMessage("unknownInput");
                break;
            }
        }
    }

    public static class Ui {

        public Ui() {

        }

        /**
         * Greets the user with a fancy image.
         */
        public void greet() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___| uwu\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Nyahello! I'm Duwuke, your neighbourhood disgusting weeb bot!\n" +
                    "What can I do for you? uwu");
        }

        public void showLoadingError() {
            System.out.println("Something went wrong with the storage loading uwu");
        }

        public void emptyListMessage(){
            System.out.println("☹ OOPS!!! Your list is currently empty uwu.");
        }

        public void showTaskListSize(int listSize) {
            System.out.println("Now you have " + listSize + " task(s) in the list uwu");
        }

        /**
         * Prints an error message as required.
         *
         * @param s The error message to be printed.
         */
        public void errorMessage(String s) {
            switch (s) {
            case "unknownInput":
                System.out.println("☹ OOPS!!! Sumimasen, but I don't know what that means T^T");
                break;
            case "invalidDelete":
                System.out.println("☹ OOPS!!! Please indicate a valid task to delete uwu");
                break;
            case "invalidEvent":
                System.out.println("☹ OOPS!!! Please define your event properly uwu.");
                break;
            case "dateTimeError":
                System.out.println("☹ OOPS!!! Please define your todo date/time in the " +
                        "YYYY-MM-DD HH:MM format uwu.");
                break;
            case "invalidDeadline":
                System.out.println("☹ OOPS!!! Please define your deadline properly uwu.");
                break;
            case "invalidTodo":
                System.out.println("☹ OOPS!!! Please define your todo properly uwu.");
                break;
            case "invalidDone":
                System.out.println("☹ OOPS!!! Please indicate a valid task to complete uwu");
                break;
            }
        }

        public void deleteTask(Task t) {
            System.out.println("Noted. I've removed this task uwu:");
            System.out.println(t);
        }

        public void taskAdded(Task t) {
            System.out.println("Hai. I've added this task:");
            System.out.println("    " + t);
        }

        public void byeBye() {
            System.out.println("Bye, hope to see you again! uwu");
        }

        public void setDone(Task t) {
            System.out.println("Sugoi! I've marked this task as done uwu:");
            System.out.println(t);
        }

        /**
         * Prints the given TaskList in a user-friendly format.
         *
         * @param tl The TaskList to be printed.
         */
        public void showList(TaskList tl) {
            System.out.println("Here are the tasks in your list uwu:");
            int counter = 1;
            for (Task t : tl.getList()) {
                System.out.println(counter + ". " + t);
                counter++;
            }
        }

        public void showSearchList(TaskList tl) {
            if (tl.isEmpty()) {
                System.out.println("There are no matching tasks uwu. " +
                        "(just like how whoever wrote this has no friends.)");
            } else {
                System.out.println("Here are the matching tasks in your list uwu: ");
                int counter = 1;
                for (Task t : tl.getList()) {
                    System.out.println(counter + ". " + t);
                    counter++;
                }
            }
        }

    }

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);

        try {
            taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        FastIO fio = new FastIO();
        Parser parser = new Parser();

        ui.greet();

        // Continuously read input and performs commands until the user enters "bye"
        while (true) {
            String input = fio.nextLine();
            parser.parse(taskList, input);
            if (input.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("test/duke.txt").run();
    }
}
