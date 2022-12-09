import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Workshop extends AbstractEvent{
    private ArrayList<Workshop> prerequisites;

    public Workshop(String name, Location location, LocalDate date, double price, int numTickets, ArrayList<Workshop> pre) throws Exception {
        super(name, location, date, price, numTickets);
        assert pre!=null;
        this.prerequisites=new ArrayList<Workshop>(pre);
    }

    public Workshop(String name, LocalDate date, ArrayList<Workshop> pre){
        super(name, date);
        assert pre!=null;
        this.prerequisites=new ArrayList<Workshop>(pre);
    }

    public List<Workshop> getPrerequisites() {
        return Collections.unmodifiableList(this.prerequisites);
    }

    public void setPrerequisites(ArrayList<Workshop> prerequisites) {
        assert prerequisites!=null;
        this.prerequisites = new ArrayList<Workshop>(prerequisites);
    }

    /**
     * Accept a visitor
     * @param v visitor
     */
    @Override
    public <T> T acceptVisitor(Visitor<T> v){
        return v.visitWorkshop(this);
    }
}