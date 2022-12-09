package internal_code;

import java.util.*;

public class Remote_control {
    private ArrayList<String> sequence;
    private Drone drone;


    public Remote_control(){
        this.sequence= new ArrayList<String>();
        this.drone= new Drone();
    }


    //add a command at the end of the sequence
    public void add(String command){
        if(command==null){
            System.out.println("added command can not be null");
        }else{
            this.sequence.add(command);
            System.out.println("command added");
        }
    }

    //delete a command at a specific index
    public void delete(int index){
        if(this.sequence==null){
            System.out.println("failed to remove, sequence only contains take off at the beginning, and land at the end");
            return;
        }else if(this.sequence.size()==0){
            System.out.println("failed to remove, sequence only contains take off at the beginning, and land at the end");
            return;
        }else if(this.sequence.size()+2<index||index<=0){
            System.out.println("failed to remove, index out of range");
            return;
        }else if(index==1||index==this.sequence.size()+2) {
            System.out.println("failed to remove the first or last movement");
            return;
        }else{
            this.sequence.remove(index-2);
            System.out.println("Successfully removed");
        }
    }

    //return a clone of the sequence of movement including the takeoff at the beginning and land at the end.
    public ArrayList<String> access(){
        ArrayList<String> givenlist=new ArrayList<String>();
        givenlist.add("take off");
        if(this.sequence!=null){
            for(String l : this.sequence){
                givenlist.add(l);
            }
        }
        givenlist.add("land");
        return givenlist;
    }

    public void execute(){
        this.drone.action(this.sequence);
    }

}
