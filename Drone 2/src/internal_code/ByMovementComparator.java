package internal_code;

import java.util.ArrayList;

public class ByMovementComparator implements java.util.Comparator<Flight> {
    @Override
    public int compare(Flight f1, Flight f2) {
        return f1.getNumOfUniqueMovement()-f2.getNumOfUniqueMovement();

    }

}
