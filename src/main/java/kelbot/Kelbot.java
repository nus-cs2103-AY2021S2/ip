package kelbot;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Kelbot {
    
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    
    public Kelbot(java.nio.file.Path path) {
        ui = new UI();
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (KelbotException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }
    
    public void run() {
        while (true) {
            try {
                Parser parser = ui.takeInput();
                
                Command command = parser.getCommand();
                String taskName = parser.getTaskName();
                int taskNumber = parser.getTaskNumber();
                LocalDate date = parser.getDate();
                
                if (command == Command.BYE) {
                    ui.sayGoodbye();
                    break;
                } else if (command == Command.LIST) {
                    ui.printList(taskList);
                } else if (command == Command.DONE || command == Command.DELETE) {
                    try {
                        if (taskNumber == 0) {
                            throw new KelbotException("Which task did you finish?");
                        } else if (command == Command.DONE) {
                            Task task = taskList.complete(taskNumber);
                            ui.printDone(task);
                        } else {
                            try {
                                Task task = taskList.delete(taskNumber);
                                ui.printDelete(task);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("The list is empty");
                            }
                        }
                    } catch (KelbotException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        if (taskName == "") {
                            throw new KelbotException("Task name cannot be empty!");
                        } else if (command == Command.TODO) {
                            TodoTask newTodoTask = new TodoTask(taskName);
                            taskList.add(newTodoTask);
                            ui.printAdd(newTodoTask, taskList.getSize());
                        } else if (date == null) {
                            throw new KelbotException("Date cannot be empty!");
                        } else if (command == Command.DEADLINE) {
                            DeadlineTask newDeadlineTask = new DeadlineTask(taskName, date);
                            taskList.add(newDeadlineTask);
                            ui.printAdd(newDeadlineTask, taskList.getSize());
                        } else {
                            EventTask newEventTask = new EventTask(taskName, date);
                            taskList.add(newEventTask);
                            ui.printAdd(newEventTask, taskList.getSize());
                        }
                    } catch (KelbotException e) {
                        System.out.println(e.getMessage());
                    }
                }
                storage.save(taskList.getTaskList());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date");
            }
        }
    }
    
    public TaskList getTaskList() {
        return taskList;
    }
    
    public static void main(String[] args) throws KelbotException {
        java.nio.file.Path path = java.nio.file.Paths.get(".", "data", "Kelbot.txt");
        new Kelbot(path).run();
    }
}