import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {

    private Storage storage;

    private TaskList tasks;

    private Ui ui;

    String name;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
        name = "Link";
    }



    public void run() {
        int listCounter;
        String userInput;
        ui.println("Hello! I'm " + name);
        ui.println("How are you feeling today?");

        try {
            while (ui.canRead()) {
                userInput = ui.read();
                if (userInput.equals("bye")) {
                    break;
                }
                while (!userInput.equals("bye")) {
                    listCounter = 1;
                    String copy = userInput;
                    String[] parts = copy.split(" ");
                    String keyword = parts[0];
                    if (userInput.equals("list")) {
                        ui.println("Here are the tasks in your list:");
                        while (tasks.size() != listCounter) {
                            ui.println(listCounter + "." + tasks.get(listCounter - 1).toString());
                            listCounter++;
                        }
                        ui.println(listCounter + "." + tasks.get(listCounter - 1).toString());
                        userInput = ui.read();
                    } else if (keyword.equals("done")) {
                        if (parts.length > 2) {
                            throw new InsufficientArgumentsException("Wrong arguments");
                        }
                        int index = Integer.parseInt(parts[1]);
                        ui.println(tasks.get(index - 1).markAsDone());
                        storage.save();
                        if (ui.canRead()) {
                            userInput = ui.read();
                            if (userInput.equals("bye")) {
                                break;
                            }
                        }
                    } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
                        if (parts.length == 1) {
                            throw new InsufficientArgumentsException("Insufficient arguments provided");
                        }
                        ui.println("Got it. I've added this task:");
                        StringBuilder str = new StringBuilder();
                        if (keyword.equals("todo")) {
                            for (int i = 1; i < parts.length; i++) {
                                str.append(" ");
                                str.append(parts[i]);
                            }
                            String taskString = str.toString();
                            Todo todo = new Todo(taskString);
                            tasks.add(todo);
                            ui.println(todo.toString());
                        } else {
                            int slashIndex = 0;
                            for (int i = 0; i < parts.length; i++) {
                                if (parts[i].contains(Character.toString('/'))) {
                                    slashIndex = i;
                                }
                            }
                            for (int j = 1; j < slashIndex; j++) {
                                str.append(" ");
                                str.append(parts[j]);
                            }
                            String taskString = str.toString();
                            StringBuilder byStringBuilder = new StringBuilder();
                            for (int k = slashIndex + 1; k < parts.length; k++) {
                                if (k != slashIndex + 1) {
                                    byStringBuilder.append(" ");
                                }
                                byStringBuilder.append(parts[k]);
                            }
                            String DateString = byStringBuilder.toString();
                            LocalDate date = LocalDate.parse(DateString);
                            if (keyword.equals("deadline")) {
                                Deadline deadline = new Deadline(taskString, date);
                                tasks.add(deadline);
                                ui.println(deadline.toString());
                            } else {
                                Event event = new Event(taskString, date);
                                tasks.add(event);
                                ui.println(event.toString());
                            }
                        }
                        ui.println("Now you have " + tasks.size() + " tasks in the list.");
                        storage.save();
                        if (ui.canRead()) {
                            userInput = ui.read();
                            if (userInput.equals("bye")) {
                                break;
                            }
                        }
                    } else if (keyword.equals("delete")) {
                        if (parts.length > 2) {
                            throw new InsufficientArgumentsException("Wrong arguments");
                        }
                        int index = Integer.parseInt(parts[1]);
                        ui.println("Noted. I've removed this task:");
                        ui.println(tasks.get(index - 1).toString());
                        tasks.remove(index-1);
                        ui.println("Now you have " + tasks.size() + " tasks in the list.");

                        storage.save();
                        if (ui.canRead()) {
                            userInput = ui.read();
                            if (userInput.equals("bye")) {
                                break;
                            }
                        }
                    } else if (keyword.equals("find")) {
                        if (parts.length == 1) {
                            throw new InsufficientArgumentsException("Insufficient arguments provided");
                        }
                        ui.println("Here are the matching tasks in your list");
                        StringBuilder str = new StringBuilder();
                        for (int i = 1; i < parts.length; i++) {
                            str.append(" ");
                            str.append(parts[i]);
                        }
                        String findString = str.toString();
                        for (Task t: TaskList.getTasklist()) {
                            if (t.toString().contains(findString)) {
                                ui.println(t.toString());
                            }
                        }
                        if (ui.canRead()) {
                            userInput = ui.read();
                            if (userInput.equals("bye")) {
                                break;
                            }
                        }
                    } else {
                        throw new IllegalKeywordException("Invalid keyword");
                    }
                }
                if (userInput.equals("bye")) {
                    break;
                }
            }
            ui.println("Bye. Talk to me anytime!");
        } catch (InsufficientArgumentsException err) {
            ui.println("Number of arguments is invalid. Try again");
        } catch(IllegalKeywordException err) {
            ui.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (NumberFormatException n) {
            ui.println("Done command must be followed by a number");
        } catch (IOException e) {
            ui.println("Error writing to file");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}