package ip.src.main.java;
public class Ui {
    protected Duke bot;

    Ui(Duke bot) {
        this.bot = bot;
    }

    public void doneCommand(int id) {
        this.bot.markTaskAsDone(id);
    }

    public void toDoCommand(String input) {
        ToDo newTask = new ToDo(input);
        this.bot.addToList(newTask);
    }

    public void eventCommand(String content, String at) {
        Event newTask = new Event(content, at);
        this.bot.addToList(newTask);

    }

    public void deadlineCommand(String content, String by) {
        Deadline newTask = new Deadline(content, by);
        this.bot.addToList(newTask);
    }

    public void deleteCommand(int id){
        this.bot.deleteTask(id);
    }








}
