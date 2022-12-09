package internal_code;

import java.util.*;

public abstract class Trick implements Iterable<Movement>{
    private boolean recorded;
    private Formats type;
    private ArrayList<Movement> sequence;



    //execute the trick
    abstract void Execute();

    public abstract Iterator<Movement> iterator();


}
