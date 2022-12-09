import java.time.LocalDate;
import java.util.ArrayList;

class Concert extends VipEvent{
    private String artist;

    public Concert(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<String> Vips, String artist) throws Exception {
        super(name, location, date, price, numTickets, Vips);
        assert artist!=null;
        this.artist=artist;
    }
    public Concert(String name, LocalDate date, ArrayList<String> Vips, String artist){
        super(name,date,Vips);
        this.artist=artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        assert artist!=null;
        this.artist = artist;
    }

    /**
     * Accept a visitor
     * @param v visitor
     */
    @Override
    public <T> T acceptVisitor(Visitor<T> v){
        return v.visitConcert(this);
    }
}