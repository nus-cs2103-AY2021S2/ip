public class Todo extends Task{
    /**
     * Constructor of Todo class.
     *
     * @param name name of todo.
     * @param isDone event status.
     *
     */
     Todo(String name,Boolean isDone) {
        super(name, isDone);
     }

    /**
     * Mark task as done
     *
     * @return new task, marked as done.
     */

     @Override
     Todo finish() {
         return new Todo(this.getName(), true);
     }

     @Override
     public String toString(){
         if(this.getIsDone()){
             return "[T][X] " + this.getName();
         }
         else {
             return "[T][_] " + this.getName();
         }
     }
}
