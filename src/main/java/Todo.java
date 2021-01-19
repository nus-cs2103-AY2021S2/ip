public class Todo extends Task{
        Todo(String name, int id,Boolean isDone){
            super(name, id, isDone);
        }
        @Override
        Todo finish(){
            return new Todo(this.getName(),this.getId(), true) ;
        }

        @Override
        public String toString(){
            if(this.getIsDone()){
                return "[T][X] "+ this.getId() + "." + this.getName();
            }
            else{
                return "[T][ ] "+ this.getId() + "." + this.getName();
            }
    }

}
