package data;

import commands.Command;
import data.exception.DukeIllegalArgumentException;
import data.exception.DukeUnrecognizedArgumentException;
import data.task.*;

import storage.TaskManager;

/**
 * Main data.Duke chatbot class.
 */
public class Duke implements IDuke{
    /** List of Tasks */
    private final TaskList list;
    private final TaskManager tm;

    private Duke(TaskList lst, TaskManager tm){
        this.list = new TaskList(lst.getList());
        this.tm = tm;
    }

    /**
     * Returns a new data.Duke chatbot object.
     *
     * @return New data.Duke chatbot object.
     */
    public static Duke getDuke(String filePath) {
        TaskManager tm = TaskManager.getTaskManager(filePath);
        return new Duke(new TaskList(tm.read()), tm);
    }

    @Override
    public TaskManager getTaskManager(){
        return tm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String greeting() {
        return Ui.greet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTask(ITask task) {
        this.list.add(task);
    }


    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException
     */
    @Override
    public ITask getTask(int id) {
        if (id - 1 > list.size() || id < 0) {
            throw new IllegalArgumentException("Task id out of bound!");
        }
        return list.get(id - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskList getTasks() {
        return list;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public int numOfTasks() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handleCommand(String command) {
        try {
            Command c = Parser.parse(command);
            return c.setDuke(this).execute();
        } catch (DukeIllegalArgumentException e) {
            String output = "Meow?!! " + e.getMessage();
            System.out.print(output);
            return output;
        } catch (DukeUnrecognizedArgumentException e) {
            String output = Message.CAT_DOUBT.toString();
            System.out.print(TextFormatter.getFormattedText(output));
            return output;
        } catch (Exception e) {
            String output = Message.CAT_CRY.toString() + e;
            System.out.print(TextFormatter.getFormattedText(output));
            return output;
        }
    }



    @Override
    public String getResponse(String input) {
        return handleCommand(input);
    }

}