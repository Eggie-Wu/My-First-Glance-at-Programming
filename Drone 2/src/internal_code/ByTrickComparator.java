package internal_code;


import java.util.*;

public class ByTrickComparator implements java.util.Comparator<Flight> {

    @Override
    public int compare(Flight f1, Flight f2) {

        return  f1.getNumOfTricks()-f2.getNumOfTricks();
    }
}
