import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class MyDuke {

    static Storage storage;
    static TaskList tasks;
    static Ui ui;
    static String DATA_SAVE_FILE_DIR = "../data/saveFile.txt";

    MyDuke(String filePath) {
        ui = new Ui();
        ui.showGreetingMsg();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showFileLoadSuccessMsg();
        } catch (MyDukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MyDuke myDuke = new MyDuke(DATA_SAVE_FILE_DIR);
        ui.askForUserInput();

        String input = ui.getInput(sc);
        Parser parser = new Parser(input, storage, tasks, ui);

        while (!parser.isGoodbye()) {
            parser.handleInput();
            input = ui.getInput(sc);
            parser = parser.parseNextInput(input);
        }
        ui.showByeMsg();

        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (IOException e) {
            String newDir = "../data/saveFile.txt";
            ui.printErrorMsg("Something went wrong: " + e.getMessage());
        }
        sc.close();
    }

    static void indexChecker(String[] inputArr) throws NoIndexException {
        if (inputArr.length == 1) {
            throw new NoIndexException(
                    "Paikia Bot: you done what task, limpeh need more information ah. "
                            + "input 'done <task number>'. eg, done 3");
        }
    }

    static void todoChecker(String[] inputArr) throws NoToDoException {
        if (inputArr.length == 1) {
            throw new NoToDoException(
                    "Paikia Bot: you want to add what todo task, limpeh need more information ah. "
                            + "input 'todo <info>. eg, todo read book");
        }
    }

    static void eventChecker(String[] inputArr) throws NoEventException, NoDateException {
        if (inputArr.length == 1) {
            throw new NoEventException(
                    "Paikia Bot: you want to add what event task, limpeh need more information ah. "
                            + "input 'event <info> /YYYY-MM-DD. eg, event bookfest /at 2020-08-24");
        } else if (inputArr[1].split("/", 2).length == 1) {
            throw new NoDateException(
                    "Paikia Bot: i dun see any date inputs leh. to add date input, use '/YYYY-MM-DD'. "
                            + "eg, event bookfest /at 2020-08-24");
        }
    }

    static void deadlineChecker(String[] inputArr) throws NoDeadlineException, NoDateException {
        if (inputArr.length == 1) {
            throw new NoDeadlineException(
                    "Paikia Bot: you want to add what deadline task, limpeh need more information ah. "
                            + "input 'deadline <info> <date>. eg, deadline return book /by 2020-01-01");
        } else if (inputArr[1].split("/", 2).length == 1) {
            throw new NoDateException(
                    "Paikia Bot: i dun see any date inputs leh. to add date input, use '/YYYY-MM-DD'. "
                            + "eg, deadline return book /by 2020-01-01");
        }
    }
}

class Parser {

    String input;
    Storage storage;
    TaskList tasks;
    Ui ui;

    Parser(Storage storage, TaskList tasks, Ui ui) {
        this.input = "";
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    Parser(String input, Storage storage, TaskList tasks, Ui ui) {
        this.input = input;
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    Parser parseNextInput(String input) {
        return new Parser(input, storage, tasks, ui);
    }

    boolean isGoodbye() {
        return this.input.equals("bye");
    }

    String[] getParsedInput() {
        return this.input.split(" ", 2);
    }

    void handleInput() {
        switch (getParsedInput()[0]) {
        case "list":
            int counter = 1;
            String[] tempArr = new String[100];
            if (tasks.getTaskList().isEmpty()) { // improved implementation in case list is empty, gives a clear output
                ui.showListEmptyMsg();
            } else {
                for (Task t : tasks.getTaskList()) { // changed String s to Task t
                    tempArr[counter - 1] = counter + ". " + t.toString();
                    counter++;
                }
                ui.printTasksInList(tempArr);
            }
            break;
        case "done":
            try {
                MyDuke.indexChecker(getParsedInput());
                int ref = Integer.parseInt(getParsedInput()[1]);
                tasks.getTaskList().set(ref - 1, tasks.getTaskList().get(ref - 1).setAsDone());
                ui.printDoneTaskAlert(tasks.getTaskList().get(ref - 1).toString());
            } catch (NoIndexException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printErrorMsg("Paikia Bot: the number that you inputted is invalid leh (more than the "
                        + "total number of tasks or less than 1), try again pls ah");
            } catch (NumberFormatException e) {
                ui.printErrorMsg("Paikia Bot: ur input after 'done' is invalid, reminder that it should be"
                        + " a single integerand remember to not leave a space after your input. eg, done 3");
            }
            break;
        case "delete":
            try {
                MyDuke.indexChecker(getParsedInput());
                int ref = Integer.parseInt(getParsedInput()[1]);
                Task toRemove = tasks.getTask(ref);
                tasks.deleteTask(ref - 1);
                ui.printDeletedTaskAlert(toRemove.toString(), tasks.getTaskList().size());
            } catch (NoIndexException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.printErrorMsg("Paikia Bot: the number that you inputted is invalid leh (more than the "
                        + " total number of tasks or less than 1), try again pls ah");
            } catch (NumberFormatException e) {
                ui.printErrorMsg("Paikia Bot: ur input after 'done' is invalid, reminder that it should be a "
                        + "single integer and remember to not leave a space after your input. eg, done 3");
            }
            break;
        case "todo":
            try {
                MyDuke.todoChecker(getParsedInput());
                ToDo todo = new ToDo(getParsedInput()[1], false);
                tasks.addTask(todo);
                ui.printAddedTodoAlert(todo.toString(), tasks.getTaskList().size());
            } catch (NoToDoException e) {
                ui.printErrorMsg(e.getMessage());
            }
            break;
        case "event":
            try {
                MyDuke.eventChecker(getParsedInput());
                String[] temp = getParsedInput()[1].split("/", 2);
                Event event = new Event(LocalDate.parse(temp[1].substring(3)), temp[0], false);
                tasks.addTask(event);
                ui.printAddedEventAlert(event.toString(), tasks.getTaskList().size());
            } catch (NoEventException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (NoDateException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printErrorMsg(new String[]{
                        "Paikia Bot: oi your date input got format error cannot parse sial: "
                                + getParsedInput()[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                });
            }
            break;
        case "deadline":
            try {
                MyDuke.deadlineChecker(getParsedInput());
                String[] temp = getParsedInput()[1].split("/", 2);
                Deadline deadline = new Deadline(LocalDate.parse(temp[1].substring(3)), temp[0], false);
                tasks.addTask(deadline);
                ui.printAddedDeadlineAlert(deadline.toString(), tasks.getTaskList().size());
            } catch (NoDeadlineException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (NoDateException e) {
                ui.printErrorMsg(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printErrorMsg(new String[]{
                        "Paikia Bot: oi your date input got format error cannot parse sial: "
                                + getParsedInput()[1].split("/", 2)[1],
                        "Paikia Bot: the format should be liddis: yyyy-mm-dd, eg. 2020-03-02"
                });
            }
            break;
        default:
            ui.showInputError();
        }
    }
}

class TaskList {

    List<Task> taskList;

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    void changeTask(Task newTask, int index) {
        taskList.set(index - 1, newTask);
    }

    void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    Task getTask(int index) {
        return taskList.get(index - 1);
    }

    List<Task> getTaskList() {
        return this.taskList;
    }

}

class Storage {

    String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    List<Task> loadTasks() throws FileNotFoundException, InvalidArgException,
            NumberFormatException, InvalidTypeException, DateTimeParseException {

        File f = new File(this.filePath); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a Scanner using the File as the source
        List<Task> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            // System.out.println(input);
            String[] inputArr = input.split(" @ ");
            if (inputArr.length < 3 && inputArr.length > 4) {
                throw new InvalidArgException("Paikia Bot: eh walao something is wrong with this input: "
                        + input + ", pls double check and rectify");
            }
            // System.out.println(inputArr.length);
            boolean isDone = Integer.parseInt(inputArr[1]) == 1;
            if (inputArr[0].equals("T")) {
                list.add(new ToDo(inputArr[2], isDone));
            } else if (inputArr[0].equals("E")) {
                list.add(new Event(LocalDate.parse(inputArr[3]), inputArr[2], isDone));
            } else if (inputArr[0].equals("D")) {
                list.add(new Deadline(LocalDate.parse(inputArr[3]), inputArr[2], isDone));
            }
        }
        sc.close();
        return list;

    }

    void saveToFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : list) {
            int doneStatus = t.isDone ? 1 : 0;
            if (t instanceof ToDo) {
                fw.write("T @ " + doneStatus + " @ " + t.info + System.lineSeparator());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                fw.write("E @ " + doneStatus + " @ " + t.info + " @ " + e.date + System.lineSeparator());
            } else {
                Deadline d = (Deadline) t;
                fw.write("D @ " + doneStatus + " @ " + t.info + " @ " + d.deadline + System.lineSeparator());
            }
        }
        fw.close();
    }

    void saveToFile(List<Task> list, String newDir) throws IOException {
        FileWriter fw = new FileWriter(newDir);
        for (Task t : list) {
            int doneStatus = t.isDone ? 1 : 0;
            if (t instanceof ToDo) {
                fw.write("T @ " + doneStatus + " @ " + t.info + System.lineSeparator());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                fw.write("E @ " + doneStatus + " @ " + t.info + " @ " + e.date + System.lineSeparator());
            } else {
                Deadline d = (Deadline) t;
                fw.write("D @ " + doneStatus + " @ " + t.info + " @ " + d.deadline + System.lineSeparator());
            }
        }
        fw.close();
    }

}

class Ui {

    public static String DASH = "_______________________________________________"
            + "_____________";

    void print(String s) {
        System.out.println(DASH);
        System.out.println(s);
        System.out.println(DASH);
    }

    String getInput(Scanner sc) {
        System.out.print("You: ");
        String input = sc.nextLine();
        System.out.println(input);
        return input;
    }

    void print(String[] sArr) {
        System.out.println(DASH);
        for (String s : sArr) {
            // Level-2 adjustments
            if (s != null) {
                System.out.println(s);
            }
        }
        System.out.println(DASH);
    }

    void showGreetingMsg() {
        print("Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.");
    }

    void showFileLoadSuccessMsg() {
        print("Pai Kia Bot: Got ur input file, baik la");
    }

    void showLoadingError() {
        print(new String[] {
                "Pai Kia Bot: I cannot find ur input file sia, could be becuz",
                "1) u never create file because u r new user, in that case, just continue using this program and i "
                        + "will create the folder and save file for u in the directory [root]/data",
                "2) ur saveFile.txt is not in the correct directory, pls input bye and shift the file to [root]/data",
                "3) ur input file is not named saveFile.txt, pls input 'bye' and rename ur input file"
        });
    }

    void askForUserInput() {
        print("Pai Kia Bot: how u want me to help u?");
    }

    void printErrorMsg(String errorMsg) {
        print(errorMsg);
    }

    void printErrorMsg(String[] errorMsg) {
        print(errorMsg);
    }

    void showListEmptyMsg() {
        print("Paikia Bot: eh list is empty leh.");
    }

    void printDoneTaskAlert(String completedTask) {
        print("Paikia Bot: ok i just help u checked this task as done -- "
                + completedTask);
    }

    void printDeletedTaskAlert(String deletedTask, int numOfRemainingTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u deleted this task -- " + deletedTask,
                "Paikia Bot: now u got " + numOfRemainingTasksInList + " item(s) in your list ah"
        });
    }

    void printAddedTodoAlert(String newTodo, int numOfTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u added this todo -- " + newTodo,
                "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        });
    }

    void printAddedEventAlert(String newEvent, int numOfTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u added this event -- " + newEvent,
                "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        });
    }

    void printAddedDeadlineAlert(String newDeadline, int numOfTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u added this event -- " + newDeadline,
                "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        });
    }

    void showInputError() {
        print("Paikia Bot: wrong input format leh, can try again onot?");
    }

    void showByeMsg() {
        print("Pai Kia Bot: Leave so soon ah? Ok can limpeh help u save your list "
                + "in [root]/data as saveFile.txt, i go sleep first");
    }

    void printTasksInList(String[] tasksArr) {
        print(tasksArr);
    }
}

class Task {
    String info;
    boolean isDone;

    Task(String s, boolean b) {
        this.info = s;
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    Task setAsDone() {
        return new Task(this.info, true);
    }

    Task setAsUndone() {
        return new Task(this.info, false);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.info;
    }
}

class ToDo extends Task {

    ToDo(String s, boolean b) {
        super(s, b);
    }

    @Override
    ToDo setAsDone() {
        return new ToDo(this.info, true);
    }

    @Override
    ToDo setAsUndone() {
        return new ToDo(this.info, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    LocalDate deadline;

    Deadline(LocalDate deadline, String s, boolean b) {
        super(s, b);
        this.deadline = deadline;
    }

    @Override
    Deadline setAsDone() {
        return new Deadline(this.deadline, this.info, true);
    }

    @Override
    Deadline setAsUndone() {
        return new Deadline(this.deadline, this.info, false);
    }

    String getTimeDisplay() { //format: MMM dd yyyy
        String year = Integer.toString(this.deadline.getYear());
        String month = this.deadline.getMonth().name().substring(0,3);
        String day = this.deadline.getDayOfMonth() > 9
                ? Integer.toString(this.deadline.getDayOfMonth())
                : "0" + Integer.toString(this.deadline.getDayOfMonth());

        return month + " " + day + " " + year;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.getTimeDisplay() + ")";
    }
}

class Event extends Task {
    LocalDate date;

    Event(LocalDate date, String s, boolean b) {
        super(s, b);
        this.date = date;
    }

    @Override
    Event setAsDone() {
        return new Event(this.date, this.info, true);
    }

    @Override
    Event setAsUndone() {
        return new Event(this.date, this.info, false);
    }

    String getTimeDisplay() { //format: MMM dd yyyy
        String year = Integer.toString(this.date.getYear());
        String month = this.date.getMonth().name().substring(0,3);
        String day = this.date.getDayOfMonth() > 9
                ? Integer.toString(this.date.getDayOfMonth())
                : "0" + Integer.toString(this.date.getDayOfMonth());

        return month + " " + day + " " + year;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.getTimeDisplay() + ")";
    }
}

class MyDukeException extends Exception {
    MyDukeException(String s) {
        super(s);
    }
}

class NoIndexException extends MyDukeException {
    NoIndexException(String s) {
        super(s);
    }
}

class NoToDoException extends MyDukeException {
    NoToDoException(String s) {
        super(s);
    }
}

class NoEventException extends MyDukeException {
    NoEventException(String s) {
        super(s);
    }
}

class NoDeadlineException extends MyDukeException {
    NoDeadlineException(String s) {
        super(s);
    }
}

class NoDateException extends MyDukeException {
    NoDateException(String s) {
        super(s);
    }
}

class InvalidArgException extends MyDukeException {
    InvalidArgException(String s) {
        super(s);
    }
}

class InvalidTypeException extends MyDukeException {
    InvalidTypeException(String s) {
        super(s);
    }
}