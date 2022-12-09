import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) throws Exception {
        EventManagement management=EventManagement.getInstance();

        ArrayList<String> VIPs=new ArrayList<String>(Arrays.asList("Eggie", "TuanTuan", "Miemie"));
        ArrayList<Workshop> pre=new ArrayList<Workshop>();

        management.addConcertEvent("concert1", Location.BellCentre, LocalDate.now(),25,50,VIPs,"kk");
        management.addComingSoonConcertEvent("concert2", LocalDate.now(),VIPs,"kk");
        management.addGalaEvent("gala1", Location.OlympicStadium, LocalDate.now().plusDays(1),30,60,VIPs);
        management.addComingSoonGalaEvent("gala2", LocalDate.now().plusDays(1),VIPs);
        management.addWorkshopEvent("workshop1", Location.ParcJeanDrapeau, LocalDate.now().plusDays(2),35,70,pre);
        management.addComingSoonWorkshopEvent("workshop2", LocalDate.now().plusDays(2),pre);
        management.addScreeningEvent("screening1", Location.BellCentre, LocalDate.now().plusDays(3),40,80, Screening.Rating.PG);
        management.addComingSoonScreeningEvent("screening2", LocalDate.now().plusDays(2),Screening.Rating.PG);
        management.addFestival("festival1",management.getHostedEvents());

        Filterer p1=new PriceRangeFilterer(10,26);
        Filterer p2=new PriceRangeFilterer(14,50);
        Filterer l1=new LocationFilterer(Location.Multiple);
        ArrayList<Filterer> filterers=new ArrayList<>(Arrays.asList(p1,p2,l1));

        FilterResult fmultiple=FilterResult.getFilterResultByAllFilterers(filterers,management.getHostedEvents());
        System.out.println(fmultiple.getTotalVIPs());
        System.out.println(fmultiple.getProfit(0.6,0.5,0.3,0.1));

    }
}
