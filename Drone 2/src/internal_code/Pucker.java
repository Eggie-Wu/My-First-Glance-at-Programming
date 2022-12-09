package internal_code;

import java.util.*;

import static internal_code.Direction.*;
import static internal_code.Formats.*;
import static internal_code.Speed.*;

public class Pucker extends Trick{
    private boolean recorded;
    private Formats type;
    private ArrayList<Movement> sequence;

    //constructor
    public Pucker(boolean recorded,Formats type){
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
        Move move1=new Move(UP,0.2,moderate,recorded);
        Turn move2=new Turn(LEFT,high,recorded);
        Turn move3=new Turn(LEFT,high,recorded);
        Turn move4=new Turn(LEFT,high,recorded);
        Turn move5=new Turn(LEFT,high,recorded);
        Move move6=new Move(DOWN,0.2,moderate,recorded);
        this.sequence.add(move1);
        this.sequence.add(move2);
        this.sequence.add(move3);
        this.sequence.add(move4);
        this.sequence.add(move5);
        this.sequence.add(move6);
    }

    @Override
    public Iterator<Movement> iterator() {
        return this.sequence.iterator();
    }

    @Override
    public void Execute() {
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
