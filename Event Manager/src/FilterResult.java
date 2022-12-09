import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterResult {
    private List<Event> aFilteredEvents = new ArrayList<Event>();

    /**
     * Private Constructor
     * @param eventsList list of events
     * @pre eventsList!=null
     */
    private FilterResult(List<Event> eventsList){
        assert eventsList!=null;
        this.aFilteredEvents=new ArrayList<Event>(eventsList);
    }

    public List<Event> getFilteredEvents() {
        return aFilteredEvents;
    }

    /**
     * get a FilterResult object by price range
     * @param lowerBound lower bound of the price range
     * @param upperBound upper bound of the price range
     * @param events list of events
     * @return a FilterResult object
     * @pre (lowerBound <= upperBound) && events!=null
     */
    public static FilterResult getFilterResultByPriceRange(double lowerBound, double upperBound, ArrayList<Event> events) {
        assert (lowerBound <= upperBound) && events!=null;
        PriceRangeFilterer priceFilter=new PriceRangeFilterer(lowerBound,upperBound);
        ArrayList<Filterer> filterList=new ArrayList<>();
        filterList.add(priceFilter);
        return getFilterResultByAllFilterers(filterList,events);
    }

    /**
     * get a FilterResult object by location
     * @param location location you want to filter
     * @param events list of events
     * @return a FilterResult object
     * @pre location!=null && events!=null
     */
    public static FilterResult getFilterResultByLocation(Location location, ArrayList<Event> events) {
        assert location!=null && events!=null;
        LocationFilterer locationFilter=new LocationFilterer(location);
        ArrayList<Filterer> filterList=new ArrayList<>();
        filterList.add(locationFilter);
        return getFilterResultByAllFilterers(filterList,events);
    }


    /**
     * Get a FilterResult object which contains events that satisfy all filters in the filterers list
     * @param filterers list of filterers
     * @param events list of events
     * @return a FilterResult object
     * @pre filterers!=null && events!=null
     */
    public static FilterResult getFilterResultByAllFilterers(ArrayList<Filterer> filterers,ArrayList<Event> events){
        assert filterers!=null && events!=null;
        ArrayList<Event> filteredEvents=new ArrayList<>(events);
        for(int i=filteredEvents.size()-1;i>=0;i--){
            for(Filterer filter:filterers){
                if(!filteredEvents.get(i).acceptVisitor(filter)){
                    filteredEvents.remove(i);
                    break;
                }
            }
        }
        return new FilterResult(filteredEvents);
    }

    /**
     * Get a FilterResult object which contains events that satisfy at least one filter in the filterers list
     * @param filterers list of filterers
     * @param events list of events
     * @return a FilterResult object
     * @pre filterers!=null && events!=null
     */
    public static FilterResult getFilterResultBySomeFilterers(ArrayList<Filterer> filterers,ArrayList<Event> events){
        assert filterers!=null && events!=null;
        ArrayList<Event> filteredEvents=new ArrayList<>();
        for(Event e:events){
            for(Filterer filter:filterers){
                if(e.acceptVisitor(filter)){
                    filteredEvents.add(e);
                    break;
                }
            }
        }
        return new FilterResult(filteredEvents);
    }



    /**
     * Calculate the total number of VIPs
     * @return total number of VIPs
     */
    public int getTotalVIPs(){
        Visitor<Integer> numVIPsVisitor = new Visitor<Integer>(){
            /**
             * @param concert
             * @return number of VIPs
             * @pre concert!=null
             */
            @Override
            public Integer visitConcert(Concert concert) {
                assert concert!=null;
                return Integer.valueOf(concert.getVIPs().size());
            }

            /**
             * @param workshop
             * @return number of VIPs
             * @pre workshop!=null
             */
            @Override
            public Integer visitWorkshop(Workshop workshop) {
                assert workshop!=null;
                return Integer.valueOf(0);
            }

            /**
             * @param gala
             * @return number of VIPs
             */
            @Override
            public Integer visitGala(Gala gala) {
                return Integer.valueOf(gala.getVIPs().size());
            }

            /**
             * @param screening
             * @return number of VIPs
             */
            @Override
            public Integer visitScreening(Screening screening) {
                return Integer.valueOf(0);
            }

            /**
             * @param festival
             * @return number of VIPs
             */
            @Override
            public Integer visitFestival(Festival festival) {
                int festivalVIPs= 0;
                for(Iterator<Event> i = festival.getEvents(); i.hasNext(); ){
                    festivalVIPs+=i.next().acceptVisitor(this).intValue();
                }
                return Integer.valueOf(festivalVIPs);
            }
        };
        int totalNumVIPs = 0;
        for(Event e : aFilteredEvents){
            totalNumVIPs+=e.acceptVisitor(numVIPsVisitor).intValue();
        }
        return totalNumVIPs;
    }

    /**
     * Calculate the total profit of a FilterResult object
     * @param perConcert profit percentage of a concert
     * @param perWorkshop profit percentage of a Workshop
     * @param perGala profit percentage of a Gala
     * @param perScreening profit percentage of a Screening
     * @return total profit
     */
    public double getProfit(double perConcert,double perWorkshop,double perGala, double perScreening){
        /**
         * Anonymous profit visitor to calculate profit
         */
        Visitor<Double> profitVisitor = new Visitor<Double>() {

            /**
             * @param concert
             * @return its profit
             */
            @Override
            public Double visitConcert(Concert concert) {
                assert concert!=null;
                if(concert.getPrice().isPresent()&&concert.getNumTickets().isPresent()){
                    return Double.valueOf(concert.getPrice().get().doubleValue()*
                            concert.getNumTickets().get().doubleValue()*perConcert);
                }else{
                    return Double.valueOf(0);
                }
            }

            /**
             * @param workshop
             * @return its profit
             */
            @Override
            public Double visitWorkshop(Workshop workshop) {
                assert workshop!=null;
                if(workshop.getPrice().isPresent()&&workshop.getNumTickets().isPresent()){
                    return Double.valueOf(workshop.getPrice().get().doubleValue()*
                            workshop.getNumTickets().get().doubleValue()*perWorkshop);
                }else{
                    return Double.valueOf(0);
                }
            }

            /**
             * @param gala
             * @return its profit
             */
            @Override
            public Double visitGala(Gala gala) {
                if(gala.getPrice().isPresent()&&gala.getNumTickets().isPresent()){
                    return Double.valueOf(gala.getPrice().get().doubleValue()*
                            gala.getNumTickets().get().doubleValue()*perGala);
                }else{
                    return Double.valueOf(0);
                }
            }

            /**
             * @param screening
             * @return its profit
             */
            @Override
            public Double visitScreening(Screening screening) {
                if(screening.getPrice().isPresent()&&screening.getNumTickets().isPresent()){
                    return Double.valueOf(screening.getPrice().get().doubleValue()*
                            screening.getNumTickets().get().doubleValue()*perScreening);
                }else{
                    return Double.valueOf(0);
                }
            }

            /**
             * @param festival
             * @return its profit
             */
            @Override
            public Double visitFestival(Festival festival) {
                double festivalProfit= 0;
                for(Iterator<Event> i = festival.getEvents(); i.hasNext(); ){
                    festivalProfit+=i.next().acceptVisitor(this).doubleValue();
                }
                return Double.valueOf(festivalProfit);
            }
        };
        double totalProfit = 0;
        for(Event e : aFilteredEvents){
            totalProfit+=e.acceptVisitor(profitVisitor).doubleValue();
        }
        return totalProfit;
    }

    public ArrayList<Event> getList(){
        return new ArrayList<>(this.aFilteredEvents);
    }
}
