import java.time.LocalDate;
import java.util.ArrayList;

class Gala extends VipEvent{
    public Gala(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<String> VIPs) throws Exception {
        super(name, location, date, price, numTickets, VIPs);
    }
    public Gala(String name, LocalDate date, ArrayList<String> Vips){
        super(name,date,Vips);
    }

    /**
     * Accept a visitor
     * @param v visitor
     */
    @Override
    public <T> T acceptVisitor(Visitor<T> v){
        return v.visitGala(this);
    }
}