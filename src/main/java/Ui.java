import java.util.ArrayList;

public class Ui {

    public String greet(Storage storage) {
        String greeting = "Hello! I'm Cher， こんにちは～ ✿.\n"
                + "What can I do for you? \nEnter help if you are lost \u263a.\n"
                + storage.taskHistory();
        return greeting;
    }

    public String sayBye() {
        String exit = "Bye bye. Hope to see you again soon <3\n" + "Wish you all the best for CS2103T\n";
        assert Duke.canExit == false : "Duke has unexpectedly exited";
        Duke.canExit = true;
        return exit;
    }

    public String taskNotExist() {
        String errorMessage = "\u2639 OOPS!!! the task you are referring to does not seem to exist :-(";
        return errorMessage;
    }

    public String contactNotExist() {
        String errorMessage = "\u2639 OOPS!!! the contact you are referring to does not seem to exist :-(";
        return errorMessage;
    }

    public String taskMissingIndex() {
        String errorMessage = "\u2639 OOPS!!! you need to enter the index of the task :-(";
        return errorMessage;
    }

    public String contactMissingIndex() {
        String errorMessage = "\u2639 OOPS!!! you need to enter the index of the contact :-(";
        return errorMessage;
    }

    public String noTask() {
        String message = "You have no task for now, yay!";
        return message;
    }

    public String listingTasksTitle(int numOfTasks) {
        return numOfTasks == 1 ? "Here is the task in your list:"
                : "Here are the tasks in your list:";
    }

    public String matchingTasksTitle(int numOfTask) {
        return (numOfTask == 1 ? "Here is the matching task in your list:"
                : "Here are the matching tasks in your list:");
    }

    public String doneConfirmMessage(String doneTask) {
        return "Nice! I've marked this task as done:\n"
                + doneTask;
    }

    public String addTaskConfirmMessage(String addedTask) {
        return "Got it. I've added this task: \n" + addedTask;
    }

    public String deleteTaskConfirmMessage(String deletedTask) {
        return "Noted. I've removed this task: \n" + deletedTask;
    }

    public String noMatchingTaskMessage() {
        return "You have no matching task in the list";
    }

    public String taskNumberReminder(int numOfTasks) {
        if (numOfTasks <= 1) {
            return "\nNow you have " + numOfTasks + " task in the list.";
        } else {
            return "\nNow you have " + numOfTasks + " tasks in the list.";
        }
    }

    public String printTasks(ArrayList<Task> tasks) {
        int i = 1;
        String mytasks = "\n";
        for (Task t : tasks) {
            mytasks += i + ". " + t.toString() + "\n";
            i++;
        }
        return mytasks;
    }

   public String addContactConfirmMessage(String addedContact) {
        return "Got it. I've added this contact: \n" + addedContact;
   }

   public String noContact() {
        return "\u2639 OOPS!!! your phone book is empty now...";
   }

    public String listingContactsTitle(int numOfContacts) {
        return numOfContacts == 1 ? "Here is the contact in your phone book:"
                : "Here are the contacts in your phone book:";
    }

    public String printContacts(ArrayList<Contact> contacts) {
        int i = 1;
        String myContacts = "\n";
        for (Contact c : contacts) {
            myContacts += i + ". " + c.toString() + "\n";
            i++;
        }
        return myContacts;
    }

    public String removeContactConfirmMessage(String removedContact) {
        return "Noted. I've removed this contact: \n" + removedContact;
    }

    public String noMatchingContactMessage() {
        return "you have no matching contact in the phonebook";
    }

    public String matchingContactsTitle(int numOfContact) {
        return (numOfContact == 1 ? "Here is the matching contact in your phone book:"
                : "Here are the matching contacts in your phone book:");
    }

    public String help() {
        String help = "\u2605 To manage your tasks, you can:\n"
                + "1. add a todo by entering: todo (description).\n"
                + "2. add a deadline by entering: deadline (description) /by yyyy-MM-dd.\n"
                + "3. add an event by entering: event (description) /at yyyy-MM-ddTHH:mm.\n"
                + "4. mark a task as done by entering: done (index of the task).\n"
                + "5. delete a task by entering: delete (index of the task.\n"
                + "6. find a task by entering: find (keyword)\n"
                + "7. view all tasks by entering: list\n\n"
                + "\u2605 To manage your contacts, you can:\n"
                + "1. add a contact by entering: contact (name) (number)\n"
                + "2. remove a contact by entering: remove (index of the contact)\n"
                + "3. find a contact by entering: search (keyword)\n"
                + "4. view all contacts by entering: phone book";
        return help;
    }


}
