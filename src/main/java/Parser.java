public class Parser {

    public static String command(String text) {
        String[] commandLine = text.split(" ");
        return commandLine[0];
    }

    public static String description(String text) {
        String command = command(text);
        text = text.replaceFirst(command + " ", "");
        String description = "";

        switch (command) {
        case "event":
            description = text.split(" /at")[0].replaceFirst("event ", "");
            break;
        case "deadline":
            description = text.split(" /by")[0].replaceFirst("deadline ", "");
            break;
        case "todo":
            description = text;
            break;
        default:
            break;
        }

        return description;
    }

    public static String date(String text) {
        String command = command(text);
        text = text.replaceFirst(command + " ", "");
        String date = "";

        switch (command) {
        case "event":
            date = text.split(" /at ")[1];
            break;
        case "deadline":
            date = text.split(" /by ")[1];
            break;
        default:
            break;
        }

        return date;
    }

}
