import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Question1Test {
    Song s1=new Song("Title1","Artist1");
    Song s2=new Song("Title2","Artist2");
    Podcast p1=new Podcast("Name1","Host1");
    Podcast.Episode e1= p1.new Episode("Title1",1);
    Podcast.Episode e2= p1.new Episode("Title2",2);
    PlayList P1=new PlayList("PlayList1");
    PlayList P2=new PlayList("PlayList2");
    PlayableManager m =new PlayableManager();


    @BeforeEach
    void setUp() {
        P1.addPlayable(s1);
        P1.addPlayable(s2);
        P1.addPlayable(e1);
        P1.addPlayable(e2);

        P2.addPlayable(s2);
        P2.addPlayable(e1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCloneSong1() {
        Song clone1=s1.clone();
        assertEquals(clone1.getTitle(),"Title1");
    }
    @Test
    void testCloneSong2() {
        Song clone1=s1.clone();
        assertEquals(clone1.getArtist(),"Artist1");
    }
    @Test
    void testCloneSong3() {
        Song clone1=s2.clone();
        assertFalse(s1.equals(clone1));
    }

    @Test
    void testCloneSong4() {
        Song clone1=null;
        assertFalse(s1.equals(clone1));
    }

    @Test
    void testCloneEpisode1() {
        Podcast.Episode clone1=e1.clone();
        assertEquals(e1,clone1);
    }
    @Test
    void testCloneEpisode2() {
        Podcast.Episode clone1=e2.clone();
        assertFalse(e1.equals(clone1));
    }

    @Test
    void testCloneEpisode3() {
        Podcast.Episode clone1=null;
        assertFalse(e1.equals(clone1));
    }

    @Test
    void testClonePlaylist1() {
        PlayList clone1=P1.clone();
        assertEquals(P1,clone1);
    }

    @Test
    void testClonePlaylist2() {
        PlayList clone1=P2.clone();
        assertFalse(P1.equals(clone1));
    }

    @Test
    void testDefaultPlayable1() {
        Song s3=new Song("DefaultTitle","DefaultArtist");
        Playable test=m.createPlayable();
        assertTrue(s3.equals(test));
    }

    @Test
    void testDefaultPlayable2() {
        m.setPrototype(P2);
        Playable test=m.createPlayable();
        assertEquals(P2,test);
    }


}