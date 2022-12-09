package internal_code;

import java.util.*;

public class Flight {
    final private String name;
    final private ArrayList<Trick> group;


    public Flight(String name){
        this.name = name;
        this.group=new ArrayList<Trick>();
    }

    /**
     * Name getter
     * @return flight name
     */
    public String getName() {
        return this.name;
    }

    public void addTrick(Trick t){
        if(t==null){
            System.out.println("trick couldn't be null");
            return;
        }
        this.group.add(t);
    }

    void execute(){
        if(this.group==null){
            System.out.println("The drone did nothing");
            return;
        }else if(this.group.size()==0){
            System.out.println("The drone did nothing");
            return;
        }else{
            for(int i=0;i<this.group.size();i++){
                this.group.get(i).Execute();
            }
        }
    }



    //return number of unique directions in the flight
    public int queryDirections(){
        if(this.group==null){
            return 0;
        }else if(this.group.size()==0){
            return 0;
        }else{
            Set<Direction> directionSet = new HashSet();
            for(int i=0;i<this.group.size();i++){
                for(Movement m: this.group.get(i)){
                    directionSet.add(m.getDirection());
                }
            }
            return directionSet.size();
        }
    }

    //return number of tricks in the flight
    int getNumOfTricks(){
        if(this.group==null){
            return 0;
        }else {
            return this.group.size();
        }
    }

    //return number of unique movements in the flight
    int getNumOfUniqueMovement(){
        if(this.group==null){
            return 0;
        }else if(this.group.size()==0){
            return 0;
        }else{
            Set<Movement> movementsSet = new HashSet();
            for(int i=0;i<this.group.size();i++){
                for(Movement m: this.group.get(i)){
                    movementsSet.add(m);
                }
            }
            return movementsSet.size();
        }
    }

    //overide toString for printing
    @Override
    public String toString(){
        return this.getName();
    }
}
