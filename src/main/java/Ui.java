/**
 * Class which deals with the UI interactions with the user.
 */
class Ui {
    /**
     * Gets the line for formatting the output message
     *
     * @return String representing a dotted line
     */
    public String getLine() {
        String formatLine = "...................................................................";
        return formatLine;
    }

    /**
     * Greets the user
     */
    public void greetUser() {
        System.out.println(this.getLine() + "\nHey, "
                + "I am Duke.\nHow can I help you?\n"
                + this.getLine());
    }


}
