package exceptions;

public class DukeEmptyListException extends DukeException{
    @Override
    public String toString() {
        return "Cannot delete task in empty list.";
    }
}
