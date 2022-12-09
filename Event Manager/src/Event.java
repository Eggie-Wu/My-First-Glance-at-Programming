import java.time.LocalDate;
import java.util.Optional;

/*
Representation of a type of Event that can exist
 */
public interface Event extends Comparable<Event> {
    String getName();
    Optional<Location> getLocation();
    LocalDate getDate();
    Optional<Double> getPrice();
    Optional<Integer> getNumTickets();
    void setLocation(Location location)throws Exception ;
    void setPrice(double price);
    void setNumTickets(int numTickets);
    <T> T acceptVisitor(Visitor<T> v);
}
