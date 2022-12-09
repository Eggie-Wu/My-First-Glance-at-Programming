import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class VipEvent extends AbstractEvent{
    private List<String> VIPs;

    protected VipEvent(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<String> Vips) throws Exception {
        super(name, location, date, price, numTickets);
        assert Vips!=null;
        this.VIPs= new ArrayList<>(Vips);
    }

    protected VipEvent(String name, LocalDate date, ArrayList<String> Vips){
        super(name, date);
        assert Vips!=null;
        this.VIPs= new ArrayList<>(Vips);
    }

    public List<String> getVIPs(){
        return Collections.unmodifiableList(this.VIPs);
    }

    public void setVIPs(ArrayList<String> Vips){
        assert Vips!=null;
        this.VIPs= new ArrayList<>(Vips);
    }
}
