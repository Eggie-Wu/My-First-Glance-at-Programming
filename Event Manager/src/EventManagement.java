import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private List<Event> aHostedEvents = new ArrayList<Event>();
    private static final EventManagement instance =new EventManagement();

    /**
     * private constructor for singleton
     */
    private EventManagement(){}

    public static EventManagement getInstance(){
        return instance;
    }

    /**
     * Method to host a new Concert event
     * @param name name of the event
     * @param location location of the event
     * @param date date of the event
     * @param price per-person ticket price
     * @param numTickets total number of tickets that can be issued
     * @param VIPs list of “VIPs” for the concert
     * @param artist artist who is performing
     * @throws Exception Event with duplicate location&date shouldn't be created
     * @pre name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && VIPs!=null && artist!=null
     */
    public void addConcertEvent(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<String> VIPs, String artist) throws Exception {
        assert name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && VIPs!=null && artist!=null;
        aHostedEvents.add(new Concert(name,location,date,price,numTickets, VIPs,artist));
    }

    public void addComingSoonConcertEvent(String name, LocalDate date, ArrayList<String> VIPs, String artist){
        assert name!=null && date!=null && VIPs!=null && artist!=null;
        aHostedEvents.add(new Concert(name,date,VIPs,artist));
    }

    /**
     * Method to host a new Gala event
     * @param name name of the event
     * @param location location of the event
     * @param date date of the event
     * @param price per-person ticket price
     * @param numTickets total number of tickets that can be issued
     * @param VIPs list of “VIPs” for the concert
     * @throws Exception Event with duplicate location&date shouldn't be created
     * @pre name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && VIPs!=null
     */
    public void addGalaEvent(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<String> VIPs) throws Exception {
        assert name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && VIPs!=null;
        aHostedEvents.add(new Gala(name,location,date,price,numTickets, VIPs));
    }
    public void addComingSoonGalaEvent(String name, LocalDate date, ArrayList<String> VIPs) throws Exception {
        assert name!=null && date!=null && VIPs!=null;
        aHostedEvents.add(new Gala(name, date, VIPs));
    }

    /**
     * Method to host a new Screening event
     * @param name name of the event
     * @param location location of the event
     * @param date date of the event
     * @param price per-person ticket price
     * @param numTickets total number of tickets that can be issued
     * @param rating rating of “G”, “PG”, “PG-13”, or “R”.
     * @throws Exception Event with duplicate location&date shouldn't be created
     * @pre name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && rating!=null
     */
    public void addScreeningEvent(String name, Location location, LocalDate date, double price, int numTickets, Screening.Rating rating) throws Exception {
        assert name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && rating!=null;
        aHostedEvents.add(new Screening(name,location,date,price,numTickets,rating));
    }
    public void addComingSoonScreeningEvent(String name, LocalDate date, Screening.Rating rating){
        assert name!=null && date!=null && rating!=null;
        aHostedEvents.add(new Screening(name,date,rating));
    }

    /**
     * Method to host a new Workshop event
     * @param name name of the event
     * @param location location of the event
     * @param date date of the event
     * @param price per-person ticket price
     * @param numTickets total number of tickets that can be issued
     * @param pre a list of existing Workshops that a participant must have attended before attending this one
     * @throws Exception Event with duplicate location&date shouldn't be created
     * @pre name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && pre!=null
     */
    public void addWorkshopEvent(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<Workshop> pre) throws Exception {
        assert name!=null && location!=null && location!=Location.Multiple && date!=null && price>=0 && numTickets>=0 && pre!=null;
        aHostedEvents.add(new Workshop(name,location,date,price,numTickets,pre));
    }
    public void addComingSoonWorkshopEvent(String name, LocalDate date, ArrayList<Workshop> pre){
        assert name!=null && date!=null && pre!=null;
        aHostedEvents.add(new Workshop(name,date,pre));
    }

    /**
     * Method to host a new Festival event, a Festival is Coming Soon IFF all events inside are Coming Soon
     * Nested festival is supported but avoid if possible. please don't put the festival itself in any level of sub-festival of it.
     * @param name name of the festival
     * @param events events that festival contains
     * @pre name!=null && events!=null && events.size()>0
     */
    public void addFestival(String name, ArrayList<Event> events){
        assert name!=null && events!=null && events.size()>0;
        aHostedEvents.add(new Festival(name,events));
    }

    /**
     * Return the list of hosted events on EventBrite.
     * This method assumes that Events are immutable.
     * @return the list of hosted events on EventBrite.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }
}