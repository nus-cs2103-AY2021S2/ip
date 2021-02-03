public class Parser {

    private String fullText;

    public Parser(String fullText) {
        this.fullText = fullText;
    }

    public String checkCommand() throws JustinException {
        try {

            validate(fullText);

            if (fullText.equals("bye")) {

                return "BYE";

            } else if (fullText.equals("list")) {

                return "LIST";

            } else if (fullText.contains("done")) {

                return "DONE";

            } else if (fullText.contains("deadline")) {

                return "DEADLINE";

            } else if (fullText.contains("todo")) {

                return "TODO";

            } else if (fullText.contains("event")) {

                return "EVENT";

            }
            else if (fullText.contains("delete")) {

                return "DELETE";

            } else {
                return "ADD";
            }
        } catch (JustinException m) {
            throw new JustinException(m.getMessage());
        }
    }

    static void validate(String text) throws JustinException {
        if (text.length() < 5 && text.contains("todo") ) { // case 1
            throw new JustinException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else if (text.contains("blah")) { // case 2
            throw new JustinException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (text.length() < 10 && text.contains("deadline")) { // case 3
            throw new JustinException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (text.length() < 6 && text.contains("event")) { // case 4
            throw new JustinException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

}