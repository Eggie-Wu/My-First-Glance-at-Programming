package internal_code;

import java.util.*;

import static internal_code.Direction.*;
import static internal_code.Formats.*;
import static internal_code.Speed.*;


public class TakeOff extends Trick{
    private boolean recorded;
    private Formats type;
    private ArrayList<Movement> sequence;

    //constructor
    public TakeOff(boolean recorded,Formats type){
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
        Move move1=new Move(UP,0.5,low,recorded);
        Move move2=new Move(UP,1,moderate,recorded);
        this.sequence.add(move1);
        this.sequence.add(move2);
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
