public class Task {

  private String name;
  private boolean done;

  public Task(String name) {
    this.name = name;
    this.done = false;
  }

  /*
   * Called when the task's name needs to be referenced.
   */
  public String getName() {
    return this.name;
  }

  /*
   * Called when the task has been completed by the user. It will mark the task as done.
   */
  public void complete() {
    this.done = true;
  }

  public String getStatusIcon() {
    if (this.done) {
      return "[X]";
    } else {
      return "[ ]";
    }
  }


  @Override
  public String toString() {
    return this.getStatusIcon() + this.name;
  }
}
