import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FilterResultTest {
    static ArrayList<String> VIPs=new ArrayList<String>(Arrays.asList("Eggie", "TuanTuan", "Miemie"));
    static ArrayList<Workshop> pre=new ArrayList<Workshop>();

    static Concert concert1;

    static {
        try {
            concert1 = new Concert("concert1", Location.BellCentre, LocalDate.now(),25,50,VIPs,"kk");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static Concert concertComingSoon=new Concert("concert2", LocalDate.now(),VIPs,"kk");
    static Gala gala1;
    static {
        try {
            gala1 = new Gala("gala1", Location.OlympicStadium, LocalDate.now().plusDays(1),30,60,VIPs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static Gala galaComingSoon=new Gala("gala2", LocalDate.now().plusDays(1),VIPs);
    static Workshop workshop1;
    static {
        try {
            workshop1 = new Workshop("workshop1", Location.ParcJeanDrapeau, LocalDate.now().plusDays(2),35,70,pre);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static ArrayList<Workshop> pre2=new ArrayList<Workshop>(Arrays.asList(workshop1));
    static Workshop workshopComingSoon=new Workshop("workshop2", LocalDate.now().plusDays(2),pre2);

    static Screening screening1;

    static {
        try {
            screening1 = new Screening("screening1", Location.BellCentre, LocalDate.now().plusDays(3),40,80, Screening.Rating.PG);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Screening screeningComingSoon=new Screening("screening2", LocalDate.now().plusDays(2),Screening.Rating.PG);

    /**
     * festival with all normal events
     */
    static ArrayList<Event> events1=new ArrayList<Event>(Arrays.asList(gala1,concert1,workshop1,screening1));
    static Festival festival1=new Festival("festival1",events1);

    /**
     * festival with some coming soon events
     */
    static ArrayList<Event> events2=new ArrayList<Event>(Arrays.asList(screening1,workshopComingSoon,gala1));
    static Festival festival2=new Festival("festival2",events2);

    /**
     * festival with all coming soon events should be a comming soon festival
     */
    static ArrayList<Event> events3=new ArrayList<Event>(Arrays.asList(galaComingSoon,concertComingSoon,screeningComingSoon,workshopComingSoon));
    static Festival festival3=new Festival("festival3",events3);

    /**
     * single location festival with some coming soon event
     */
    static ArrayList<Event> events4=new ArrayList<Event>(Arrays.asList(screening1,concert1,concertComingSoon));
    static Festival festival4=new Festival("festival4",events4);

    /**
     * festival with real events,coming soon events, and multiple festivals inside
     */
    static ArrayList<Event> events5=new ArrayList<Event>(Arrays.asList(screening1,festival3,concert1,festival2,festival1,
            workshopComingSoon,gala1));
    static Festival festival5=new Festival("festival5",events5);

    static ArrayList<Event> eventsAll=new ArrayList<>(Arrays.asList(concert1,concertComingSoon,gala1,galaComingSoon,workshop1,
            workshopComingSoon,screening1,screeningComingSoon,festival1,festival2,festival3,festival4,festival5));

    @Test
    void testFilterByPrice1(){
        FilterResult f1=FilterResult.getFilterResultByPriceRange(14,34,eventsAll);
        ArrayList<Event> inList1=new ArrayList<>(Arrays.asList(concert1,gala1,festival1,festival2,festival5));
        ArrayList<Event> notinList1=new ArrayList<>(Arrays.asList(workshop1,screening1,concertComingSoon,galaComingSoon,
                workshopComingSoon,screeningComingSoon,festival3,festival4));
        for(Event e:inList1){
            assertTrue(f1.getList().contains(e));
        }
        for(Event e:notinList1){
            assertFalse(f1.getList().contains(e));
        }
    }
    @Test
    void testFilterByPrice2(){
        FilterResult f2=FilterResult.getFilterResultByPriceRange(31,32,eventsAll);
        ArrayList<Event> inList2=new ArrayList<>();
        ArrayList<Event> notinList2=new ArrayList<>(Arrays.asList(workshop1,screening1,concertComingSoon,galaComingSoon,
                workshopComingSoon,screeningComingSoon,festival2,festival3,festival4,concert1,gala1,festival1,festival5));
        for(Event e:inList2){
            assertTrue(f2.getList().contains(e));
        }
        for(Event e:notinList2){
            assertFalse(f2.getList().contains(e));
        }
    }

    @Test
    void testFilterByPrice3(){
        FilterResult f3=FilterResult.getFilterResultByPriceRange(0,100,eventsAll);
        ArrayList<Event> inList3=new ArrayList<>(Arrays.asList(workshop1,screening1,concert1,gala1,festival2,festival4,festival1,festival5));
        ArrayList<Event> notinList3=new ArrayList<>(Arrays.asList(concertComingSoon,galaComingSoon,
                workshopComingSoon,screeningComingSoon,festival3));
        for(Event e:inList3){
            assertTrue(f3.getList().contains(e));
        }
        for(Event e:notinList3){
            assertFalse(f3.getList().contains(e));
        }
    }

    @Test
    void testFilterByLocation1(){
        FilterResult f1=FilterResult.getFilterResultByLocation(Location.BellCentre,eventsAll);
        ArrayList<Event> inList1=new ArrayList<>(Arrays.asList(concert1,festival4,screening1));
        ArrayList<Event> notinList1=new ArrayList<>(Arrays.asList(concertComingSoon,galaComingSoon,
                workshopComingSoon,screeningComingSoon,festival3,workshop1,gala1,festival2,festival1,festival5));
        for(Event e:inList1){
            assertTrue(f1.getList().contains(e));
        }
        for(Event e:notinList1){
            assertFalse(f1.getList().contains(e));
        }
    }

    @Test
    void testFilterByLocation2(){
        FilterResult f2=FilterResult.getFilterResultByLocation(Location.Multiple,eventsAll);
        ArrayList<Event> inList2=new ArrayList<>(Arrays.asList(festival2,festival1,festival5));
        ArrayList<Event> notinList2=new ArrayList<>(Arrays.asList(concertComingSoon,galaComingSoon,
                workshopComingSoon,screeningComingSoon,festival3,workshop1,gala1,concert1,festival4,screening1));
        for(Event e:inList2){
            assertTrue(f2.getList().contains(e));
        }
        for(Event e:notinList2){
            assertFalse(f2.getList().contains(e));
        }
    }

    @Test
    void testFilterByMultiple(){
        Filterer p1=new PriceRangeFilterer(10,26);
        Filterer p2=new PriceRangeFilterer(14,50);
        Filterer l1=new LocationFilterer(Location.Multiple);
        ArrayList<Filterer> filterers=new ArrayList<>(Arrays.asList(p1,p2,l1));

        FilterResult fmultiple=FilterResult.getFilterResultByAllFilterers(filterers,eventsAll);

        ArrayList<Event> inList=new ArrayList<>(Arrays.asList(festival2,festival1));
        ArrayList<Event> notinList=new ArrayList<>(Arrays.asList(concertComingSoon,galaComingSoon,workshopComingSoon,
                screeningComingSoon,festival3,workshop1,gala1,concert1,festival4,screening1,festival5));
        for(Event e:inList){
            assertTrue(fmultiple.getList().contains(e));
        }
        for(Event e:notinList){
            assertFalse(fmultiple.getList().contains(e));
        }
    }

    @Test
    void testGetProfit(){
        Filterer p1=new PriceRangeFilterer(10,30);
        Filterer p2=new PriceRangeFilterer(15,50);
        ArrayList<Filterer> filterers=new ArrayList<>(Arrays.asList(p1,p2));

        FilterResult fmultiple=FilterResult.getFilterResultByAllFilterers(filterers,eventsAll);

        assertEquals(fmultiple.getProfit(0.6,0.5,0.3,0.1),9430);
        assertEquals(fmultiple.getProfit(0.5,0.6,0.7,0.8),21980);
    }

    @Test
    void testGetNumVIPs(){
        Filterer p1=new PriceRangeFilterer(10,30);
        Filterer p2=new PriceRangeFilterer(15,50);
        ArrayList<Filterer> filterers=new ArrayList<>(Arrays.asList(p1,p2));

        FilterResult fmultiple=FilterResult.getFilterResultByAllFilterers(filterers,eventsAll);
        System.out.println(fmultiple.getTotalVIPs());

        assertEquals(fmultiple.getTotalVIPs(),33);
    }
}
