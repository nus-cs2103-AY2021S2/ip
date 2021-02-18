public class Parser {
    protected TaskList tasks;
    protected ContactList contacts;
    protected Ui ui;
    //user commands
    private final String listCommand = "list";
    private final String doneCommand = "done";
    private final String deleteCommand = "delete";
    private final String removeCommand = "remove"; // remove contacts
    private final String findCommand = "find"; //find tasks based on a keyword
    private final String exitCommand = "bye"; //only command that will end chat
    private final String ListContactsCommand = "phone book";
    private final String searchCommand = "search";
    private final String helpCommand = "help";
    private final String ToDos = "todo"; //tasks without any date/time attached to it
    private final String Deadlines = "deadline"; //tasks that need to be done before a specific date/time
    private final String Events = "event"; //tasks that start at a specific time and ends at a specific time
    private final String Contacts = "contact";



    public Parser(TaskList tasks, ContactList contacts, Ui ui) {
        this.tasks = tasks;
        this.contacts = contacts;
        this.ui = ui;
    }

    public String readCommand(String description) throws InvalidCommandException {

        if (description.equalsIgnoreCase(listCommand)) {
            return tasks.getTasks();

        } else if (description.equalsIgnoreCase(ListContactsCommand)) {
            return contacts.getContacts();

        } else if (description.equalsIgnoreCase(exitCommand)) {
            assert Duke.canExit == false : "Duke canExit should be false";
            Duke.canExit = true;
            return ui.sayBye();

        } else if (description.equalsIgnoreCase(helpCommand)) {
            return ui.help();

        } else if (description.toLowerCase().contains(doneCommand)) {
            try {
                int taskIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                return tasks.updateTaskStatus(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                return ui.taskNotExist();
            } catch (NumberFormatException e) {
                return ui.taskMissingIndex();
            }

        } else if (description.toLowerCase().contains(deleteCommand)) {
            try {
                int taskIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                return tasks.delete(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                return ui.taskNotExist();
            } catch (NumberFormatException e) {
                return ui.taskMissingIndex();
            }

        } else if (description.toLowerCase().contains(removeCommand)) {
            try {
                int contactIndex = Integer.parseInt(
                        description.replaceAll("[^0-9]", ""));
                return contacts.remove(contactIndex);
            } catch (IndexOutOfBoundsException e) {
                return ui.contactNotExist();
            } catch (NumberFormatException e) {
                return ui.contactMissingIndex();
            }

        } else if (description.toLowerCase().contains(findCommand)) {
            String[] seg = description.split(" ");
            String keyword = seg[seg.length - 1];
            return tasks.findTask(keyword);

        } else if (description.toLowerCase().contains(searchCommand)) {
            String[] seg = description.split(" ");
            String name = seg[seg.length - 1];
            return contacts.searchContact(name);

        } else if (description.toLowerCase().contains(Deadlines)) {
            try {
                return tasks.addDeadlines(description);
            } catch (InvalidDeadlineException e) {
                return e.toString();
            }

        } else if (description.toLowerCase().contains(Events)) {
            try {
                return tasks.addEvents(description);
            } catch (InvalidEventException e) {
                return e.toString();
            }

        } else if (description.toLowerCase().contains(ToDos)) {
            try {
                return tasks.addTodos(description);
            } catch (InvalidTodoException e) {
                return e.toString();
            }

        } else if (description.toLowerCase().contains(Contacts)) {
            return contacts.addContacts(description);

        } else {
            throw new InvalidCommandException();
        }
    }

}
