package ip.src.main.java;
public class Parser {

    Parser(){
    }

    public String getCommand(String input) {
        String command = input.split(" ")[0];
        return command;
    }

    public String toDoTask(String input){
        input = input.split(" ", 2)[1];
        return input;
    }

    public String eventTaskContent(String input) {
        input = input.split(" ", 2)[1];
        String content = input.split("/at")[0];
        return content;
    }

    public String eventTaskAt(String input) {
        input = input.split(" ", 2)[1];
        String at = input.split("/at")[1];
        return at;
    }

    public String deadlineTaskContent(String input) {
        input = input.split(" ", 2)[1];
        String content = input.split("/by")[0];
        return content;
    }

    public String deadlineTaskBy(String input) {
        input = input.split(" ", 2)[1];
        String by = input.split("/by")[1];
        return by;
    }

    public int getId(String input){
        int id = Integer.valueOf(input.split(" ")[1]);
        return id;
    }



}
