import java.time.LocalDate;

class Screening extends AbstractEvent{
    private Rating rating;

    public enum Rating{
        G,PG,PG_13,R
    }

    public Screening(String name, Location location, LocalDate date, double price, int numTickets, Rating r) throws Exception {
        super(name, location, date, price, numTickets);
        assert r!=null;
        this.rating=r;
    }

    public Screening(String name, LocalDate date, Rating r){
        super(name,date);
        assert r!=null;
        this.rating=r;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        assert rating!=null;
        this.rating = rating;
    }

    /**
     * Accept a visitor
     * @param v visitor
     */
    @Override
    public <T> T acceptVisitor(Visitor<T> v){
        return v.visitScreening(this);
    }
}