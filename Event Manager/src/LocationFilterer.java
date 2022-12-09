public class LocationFilterer implements Filterer{

    Location location;

    /**
     * constructor
     * @param location specific location to filter
     * @pre location!=null
     */
    public LocationFilterer(Location location) {
        assert location!=null;
        this.location = location;
    }

    /**
     * @param concert
     * @return Boolean whether this in event is in this location
     */
    @Override
    public Boolean visitConcert(Concert concert) {
        assert concert!=null;
        if(concert.getLocation().isPresent()){
            return Boolean.valueOf(concert.getLocation().get()==this.location);
        }
        return Boolean.FALSE;
    }

    /**
     * @param workshop
     * @return Boolean whether this in event is in this location
     */
    @Override
    public Boolean visitWorkshop(Workshop workshop) {
        assert workshop!=null;
        if(workshop.getLocation().isPresent()){
            return Boolean.valueOf(workshop.getLocation().get()==this.location);
        }
        return Boolean.FALSE;
    }

    /**
     * @param gala
     * @return Boolean whether this in event is in this location
     */
    @Override
    public Boolean visitGala(Gala gala) {
        assert gala!=null;
        if(gala.getLocation().isPresent()){
            return Boolean.valueOf(gala.getLocation().get()==this.location);
        }
        return Boolean.FALSE;
    }

    /**
     * @param screening
     * @return Boolean whether this in event is in this location
     */
    @Override
    public Boolean visitScreening(Screening screening) {
        assert screening!=null;
        if(screening.getLocation().isPresent()){
            return Boolean.valueOf(screening.getLocation().get()==this.location);
        }
        return Boolean.FALSE;
    }

    /**
     * @param festival
     * @return Boolean whether this in event is in this location
     */
    @Override
    public Boolean visitFestival(Festival festival) {
        assert festival!=null;
        if(festival.getLocation().isPresent()){
            return Boolean.valueOf(festival.getLocation().get()==this.location);
        }
        return Boolean.FALSE;
    }
}
