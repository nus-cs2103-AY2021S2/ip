public class Todo extends Task{
        Todo(String name,Boolean isDone){
            super(name, isDone);
        }
        @Override
        Todo finish(){
            return new Todo(this.getName(), true) ;
        }


        @Override
        public String toString(){
            if(this.getIsDone()){
                return "[T][X] " + this.getName();
            }
            else{
                return "[T][_] " + this.getName();
            }
    }

}
