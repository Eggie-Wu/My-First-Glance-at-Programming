package client_code;

import internal_code.*;

import java.util.*;

import static internal_code.Formats.*;

public class RunDrone {

    public static void main(String args[]) {

        // TODO: illustrate your implementations here

        Drone drone1=new Drone("cuteDrone");
        System.out.println(drone1.getName());


        Trick a=new TakeOff(true,MOV);
        //video will be saved as MP4 by default if not specified
        Trick b=new Sidewinder(true,null);
        Trick c=new Pucker(false,null);

        //have 3 tricks, 10 unique movements
        Flight f1=new Flight("f1");
        //null trick won't be added
        f1.addTrick(null);
        f1.addTrick(a);
        f1.addTrick(b);
        f1.addTrick(c);





        //execute the flight on the drone
        drone1.fly(f1);



        //have 6 tricks,5 unique movements
        Flight f2=new Flight("f2");
        f2.addTrick(a);
        f2.addTrick(c);
        f2.addTrick(c);
        f2.addTrick(c);
        f2.addTrick(c);
        f2.addTrick(c);




        //have 4 tricks,7 unique movements
        Flight f3=new Flight("f3");
        f3.addTrick(a);
        f3.addTrick(b);
        f3.addTrick(a);
        f3.addTrick(a);


        //will be 5
        System.out.println(f1.queryDirections());
        //will be 3
        System.out.println(f2.queryDirections());
        //will be 4
        System.out.println(f3.queryDirections());

        ArrayList<Flight> flightList=new ArrayList<Flight>();

        flightList.add(f1);
        flightList.add(f2);
        flightList.add(f3);

        //original order is f1 f2 f3
        System.out.println(flightList);

        //Sort in ascending order by num of tricks
        Collections.sort(flightList, new ByTrickComparator());

        //should be f1 f3 f2, as number of Tricks are 3 4 6 respectively
        System.out.println(flightList);

        //Sort in ascending order by num of unique movements
        Collections.sort(flightList, new ByMovementComparator());

        //should be f2 f3 f1, as number of unique tricks are 5 7 10 respectively
        System.out.println(flightList);

    }
}
