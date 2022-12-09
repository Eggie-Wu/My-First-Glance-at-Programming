package internal_code;

import java.util.*;

import static internal_code.Direction.*;
import static internal_code.Formats.*;
import static internal_code.Speed.*;

public class Sidewinder extends Trick{
    private boolean recorded;
    private Formats type;
    private ArrayList<Movement> sequence;

    //constructor
    public Sidewinder(boolean recorded,Formats type){
        if(recorded){
            this.recorded= true;
            if(type==null){
                this.type=MP4;
            }else{
                this.type=type;
            }
        }else{
            this.recorded= false;
            this.type=null;
        }

        this.sequence = new ArrayList<Movement>();
        Move move1=new Move(FORWARD,0.5,low,recorded);
        Move move2=new Move(RIGHT,0.5,low,recorded);
        Move move3=new Move(FORWARD,0.5,low,recorded);
        Move move4=new Move(LEFT,0.5,low,recorded);
        Move move5=new Move(FORWARD,0.5,low,recorded);
        Move move6=new Move(RIGHT,0.2,moderate,recorded);
        Move move7=new Move(FORWARD,0.2,moderate,recorded);

        this.sequence.add(move1);
        this.sequence.add(move2);
        this.sequence.add(move3);
        this.sequence.add(move4);
        this.sequence.add(move5);
        this.sequence.add(move6);
        this.sequence.add(move7);
    }

    @Override
    public Iterator<Movement> iterator() {
        return this.sequence.iterator();
    }

    void Execute() {
        if(this.recorded){
            System.out.println("Start recording");
            for(int i=0;i<this.sequence.size();i++){
                this.sequence.get(i).execute();
            }
            System.out.println("Video has been saved as "+this.type.toString()+" file.");
            return;
        }else{
            for(int i=0;i<this.sequence.size();i++){
                this.sequence.get(i).execute();
            }

        }
    }
}
