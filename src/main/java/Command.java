public abstract class Command {
//    BYE("I see you're done for now boss, see you soon!"),
//    DEADLINE("Roger that boss, I've added a new Deadline: "),
//    DELETE("Alrighty bossman. I shall wipe this task off the face of the earth: "),
//    DONE ("Impressive, yet another task has been done: "),
//    EVENT ("Roger that boss, I've added a new Event: "),
//    LIST ("Aye boss, here to see your \"collection\" eh?"),
//    TODO ("Roger that boss, I've added a new ToDo: ");

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract String toDukeOutput();
}
