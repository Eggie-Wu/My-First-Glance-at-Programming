import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventsTest {
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
    static ArrayList<Event> events5=new ArrayList<Event>(Arrays.asList(screening1,festival3,concert1,festival2,festival1,workshopComingSoon,gala1));
    static Festival festival5=new Festival("festival5",events5);




    @BeforeEach
    void setUp(){

    }

    @Test
    void testName(){
        assertEquals(concert1.getName(),"concert1");
        assertEquals(gala1.getName(),"gala1");
        assertEquals(workshop1.getName(),"workshop1");
        assertEquals(screening1.getName(),"screening1");
        assertEquals(festival1.getName(),"festival1");
    }

    @Test
    void testLocation(){
        assertEquals(concert1.getLocation().get(), Location.BellCentre);
        assertTrue(concertComingSoon.getLocation().isEmpty());

        assertEquals(gala1.getLocation().get(),Location.OlympicStadium);
        assertTrue(galaComingSoon.getLocation().isEmpty());

        assertEquals(workshop1.getLocation().get(),Location.ParcJeanDrapeau);
        assertTrue(workshopComingSoon.getLocation().isEmpty());

        assertEquals(screening1.getLocation().get(),Location.BellCentre);
        assertTrue(screeningComingSoon.getLocation().isEmpty());

        assertEquals(festival1.getLocation().get(),Location.Multiple);
        assertEquals(festival2.getLocation().get(),Location.Multiple);
        /**
         * coming soon festival
         */
        assertTrue(festival3.getLocation().isEmpty());
        /**
         * single location festival
         */
        assertEquals(festival4.getLocation().get(),Location.BellCentre);
        assertEquals(festival5.getLocation().get(),Location.Multiple);
    }

    @Test
    void testDate(){
        assertEquals(concert1.getDate(), LocalDate.now());
        assertEquals(gala1.getDate(),LocalDate.now().plusDays(1));
        assertEquals(workshop1.getDate(),LocalDate.now().plusDays(2));
        assertEquals(screening1.getDate(),LocalDate.now().plusDays(3));

        assertEquals(festival1.getDate(),LocalDate.now());
        assertEquals(festival2.getDate(),LocalDate.now().plusDays(1));
        assertEquals(festival3.getDate(),LocalDate.now());
        assertEquals(festival4.getDate(),LocalDate.now());
        assertEquals(festival5.getDate(),LocalDate.now());
    }

    @Test
    void testPrice(){
        assertEquals(concert1.getPrice().get(), 25);
        assertEquals(gala1.getPrice().get(),30);
        assertEquals(workshop1.getPrice().get(),35);
        assertEquals(screening1.getPrice().get(),40);

        assertTrue(concertComingSoon.getPrice().isEmpty());
        assertTrue(galaComingSoon.getPrice().isEmpty());
        assertTrue(workshopComingSoon.getPrice().isEmpty());
        assertTrue(screeningComingSoon.getPrice().isEmpty());

        assertEquals(festival1.getPrice().get(),26);
        assertEquals(festival2.getPrice().get(),14);
        assertTrue(festival3.getPrice().isEmpty());
        assertEquals(festival4.getPrice().get(),13);
        assertEquals(festival5.getPrice().get(),27);
    }

    @Test
    void testNumTickets(){
        assertEquals(concert1.getNumTickets().get(), 50);
        assertEquals(gala1.getNumTickets().get(),60);
        assertEquals(workshop1.getNumTickets().get(),70);
        assertEquals(screening1.getNumTickets().get(),80);

        assertTrue(concertComingSoon.getNumTickets().isEmpty());
        assertTrue(galaComingSoon.getNumTickets().isEmpty());
        assertTrue(workshopComingSoon.getNumTickets().isEmpty());
        assertTrue(screeningComingSoon.getNumTickets().isEmpty());

        assertEquals(festival1.getNumTickets().get(),50);
        assertEquals(festival2.getNumTickets().get(),60);
        assertTrue(festival3.getNumTickets().isEmpty());
        assertEquals(festival4.getNumTickets().get(),50);
        assertEquals(festival5.getNumTickets().get(),50);
    }

    @Test
    void testRating(){
        assertEquals(screening1.getRating(), Screening.Rating.PG);
        assertEquals(screeningComingSoon.getRating(), Screening.Rating.PG);
    }

    @Test
    void testArtist(){
        assertEquals(concert1.getArtist(), "kk");
        assertEquals(concertComingSoon.getArtist(), "kk");
    }

    @Test
    void testVIPs(){
        assertEquals(concert1.getVIPs().get(0), "Eggie");
        assertEquals(concertComingSoon.getVIPs().get(0), "Eggie");
        assertEquals(gala1.getVIPs().get(0), "Eggie");
        assertEquals(galaComingSoon.getVIPs().get(0), "Eggie");
    }

    @Test
    void testPrereq(){
        assertTrue(workshopComingSoon.getPrerequisites().get(0).equals(workshop1));
    }
}
