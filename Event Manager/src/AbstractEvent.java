import java.time.LocalDate;
import java.util.*;

/**
 * Abstract Event class to prevent code reuse
 */
public abstract class AbstractEvent implements Event{
    private final String name;
    private Optional<Location> location;
    private final LocalDate date;
    private Optional<Double> price;
    private Optional<Integer> numTickets;

    /**
     * A field that save existing pair of location and Date
     * The idea is the same as flyweight, but performs faster and save more space.
     * A festival will not be checked duplication, otherwise a single-location festival will never be created.
     */
    private  static HashMap<Location, HashSet<LocalDate>> existing=new HashMap<>();

    /**
     * A helper method to pre-process the list of events that a festival contain
     * @param events the list of events that a festival contain
     * @return the sorted arraylist without duplicate event
     */
    protected ArrayList<Event> processEventsList(ArrayList<Event> events){
        Set<Event> s= new HashSet<Event>();
        s.addAll(events);
        ArrayList<Event> l= new ArrayList<Event>();
        l.addAll(s);
        Collections.sort(l);
        return l;
    }

    /**
     * Template Constructor for a festival
     * @param name name of the festival
     * @param events events that festival contains
     * @pre name!=null && events!=null && events.size()>0
     */
    protected AbstractEvent(String name,ArrayList<Event> events){
        assert name!=null && events!=null && events.size()>0;
        this.name=name;
        ArrayList<Event> eventsList=processEventsList(events);
        this.date=eventsList.get(0).getDate();
        this.location=Optional.empty();
        this.numTickets=Optional.empty();
        this.price=Optional.empty();
        double totalPrice=0;
        for(Event e : eventsList){
            if(e.getLocation().isPresent()){
                if(this.location.isEmpty()){
                    this.location=e.getLocation();
                }else if(e.getLocation().get()!=this.location.get()){
                    this.location=Optional.of(Location.Multiple);
                }
            }
            if(e.getNumTickets().isPresent()){
                if(this.numTickets.isEmpty()) {
                    this.numTickets = e.getNumTickets();
                }else if(e.getNumTickets().get()<this.numTickets.get()){
                    this.numTickets=e.getNumTickets();
                }
            }
            if(e.getPrice().isPresent()){
                if(this.price.isEmpty()) {
                    this.price = Optional.of(Double.valueOf(0));
                }
                totalPrice +=e.getPrice().get().doubleValue()*0.2;
            }
        }
        if(this.price.isPresent()){
            this.price=Optional.of(Double.valueOf(totalPrice));
        }
    }

    /**
     * Template Constructor for all coming soon events other than festival
     * @param name name of the event
     * @param date date of the event
     * @pre name!=null && date!=null
     */
    protected AbstractEvent(String name, LocalDate date){
        assert name!=null && date!=null;
        this.name = name;
        this.location = Optional.empty();
        this.date = date;
        this.price =  Optional.empty();
        this.numTickets = Optional.empty();
    }

    /**
     * Template Constructor for all not coming soon events other than festival
     * @param name name of the event
     * @param location location of the event
     * @param date date of the event
     * @param price per-person ticket price
     * @param numTickets total number of tickets that can be issued
     * @pre name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0
     */
    protected AbstractEvent(String name, Location location, LocalDate date, double price, int numTickets) throws Exception {
        assert name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0;
        if(existing.containsKey(location)){
            if(existing.get(location).contains(date)){
                throw new Exception("Event with duplicate location&date shouldn't be created");
            }else{
                existing.get(location).add(date);
            }
        }else{
            HashSet<LocalDate> dates=new HashSet<>();
            dates.add(date);
            existing.put(location,dates);
        }
        this.name = name;
        this.location = Optional.of(location);
        this.date = date;
        this.price = Optional.of(Double.valueOf(price));
        this.numTickets = Optional.of(Integer.valueOf(numTickets));
    }


    /**
     * Get the name of the event
     * @return the name of the event
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     * Get the location of the event
     * @return the location of the event
     */
    @Override
    public Optional<Location> getLocation(){
        return this.location;
    }

    /**
     * Get the date of the event
     * @return the date of the event
     */
    @Override
    public LocalDate getDate(){
        return this.date;
    }

    /**
     * Get per-person ticket price
     * @return per-person ticket price
     */
    @Override
    public Optional<Double> getPrice(){
        return this.price;
    }

    /**
     * Get total number of tickets that can be issued
     * @return total number of tickets that can be issued
     */
    @Override
    public Optional<Integer> getNumTickets(){
        return this.numTickets;
    }

    /**
     * Set the location for a coming soon event
     * @pre !(this instanceof Festival) && this.getLocation().isEmpty() && location!=null && location!=Location.Multiple
     */
    @Override
    public void setLocation(Location location) throws Exception {
        assert !(this instanceof Festival) && this.getLocation().isEmpty() && location!=null && location!=Location.Multiple;
        this.location=Optional.of(location);
    }

    /**
     * Set per-person ticket price for a coming soon event
     * @pre !(this instanceof Festival) && this.getPrice().isEmpty() && price>=0
     */
    @Override
    public void setPrice(double price){
        assert !(this instanceof Festival) && this.getPrice().isEmpty() && price>=0;
        this.price=Optional.of(Double.valueOf(price));
    }

    /**
     * Set total number of tickets that can be issued for a coming soon event
     * @return !(this instanceof Festival) && total number of tickets that can be issued
     */
    @Override
    public void setNumTickets(int numTickets){
        assert !(this instanceof Festival) && this.getNumTickets().isEmpty() && numTickets>=0;
        this.numTickets=Optional.of(Integer.valueOf(numTickets));
    }


    /**
     * Override compareTo
     * @param e the event to be compared.
     * @return difference in date
     */
    @Override
    public int compareTo(Event e) {
        return this.getDate().compareTo(e.getDate());
    }
}
