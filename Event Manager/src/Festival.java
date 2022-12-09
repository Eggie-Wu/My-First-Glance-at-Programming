import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

class Festival extends AbstractEvent{
    private final ArrayList<Event> events;

    public Festival(String name, ArrayList<Event> events) {
        super(name,events);
        assert events!=null;
        ArrayList<Event> eventsList=processEventsList(events);
        this.events=eventsList;
    }

    /**
     * Recalculate the location of the festival
     * @return the location of the event
     */
    @Override
    public Optional<Location> getLocation(){
        Optional<Location> l=Optional.empty();
        for(Event e : this.events) {
            if (e.getLocation().isPresent()) {
                if (l.isEmpty()) {
                    l = e.getLocation();
                } else if (e.getLocation().get() != l.get()) {
                    l = Optional.of(Location.Multiple);
                }
            }
        }
        return l;
    }

    /**
     * Recalculate the date of the festival
     * @return the date of the event
     */
    @Override
    public LocalDate getDate(){
        ArrayList<Event> eventsList=processEventsList(this.events);
        return eventsList.get(0).getDate();
    }

    /**
     * Recalculate per-person ticket price
     * @return per-person ticket price
     */
    @Override
    public Optional<Double> getPrice(){
        Optional<Double> result=Optional.empty();
        double totalPrice=0;
        for(Event e : this.events){
            if(e.getPrice().isPresent()){
                if(result.isEmpty()) {
                    result = Optional.of(Double.valueOf(0));
                }
                totalPrice +=e.getPrice().get().doubleValue()*0.2;
            }
        }
        if(result.isPresent()){
            result=Optional.of(Double.valueOf(totalPrice));
        }
        return result;
    }

    /**
     * Recalculate total number of tickets that can be issued
     * @return total number of tickets that can be issued
     */
    @Override
    public Optional<Integer> getNumTickets(){
        Optional<Integer> result =Optional.empty();
        for(Event e : this.events){
            if(e.getNumTickets().isPresent()){
                if(result.isEmpty()) {
                    result = e.getNumTickets();
                }else if(e.getNumTickets().get()<result.get()){
                    result=e.getNumTickets();
                }
            }
        }
        return result;
    }


    public Iterator<Event> getEvents() { return events.iterator(); }
    /**
     * Accept a visitor
     * @param v visitor
     */
    @Override
    public <T> T acceptVisitor(Visitor<T> v){
        return v.visitFestival(this);
    }
}