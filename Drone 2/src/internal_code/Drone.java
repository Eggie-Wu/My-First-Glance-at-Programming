package internal_code;

public class Drone {

    final private String name;

    /**
     * Constructor
     * @param name of the drone
     */
    public Drone(String name) {
        this.name = name;
    }

    /**
     * Name getter
     * @return drone name
     */
    public String getName() {
        return this.name;
    }

    /**
     * execute a flight on this drone
     */
    public void fly(Flight flight){
        if(flight==null){
            System.out.println("The drone did nothing");
            return;
        }else{
            flight.execute();
        }

    }
}
