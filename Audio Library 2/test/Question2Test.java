import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Question2Test {

    Song s1=new Song("Title1","Artist1");
    Song s2=new Song("Title2","Artist2");
    Podcast p1=new Podcast("Name1","Host1");
    Podcast.Episode e1= p1.new Episode("Title1",1);
    Podcast.Episode e2= p1.new Episode("Title2",2);
    PlayList P1=new PlayList("PlayList1");
    PlayList P2=new PlayList("PlayList2");
    PlayList P3=new PlayList("PlayList3");
    PlayList P4=new PlayList("PlayList4");


    @BeforeEach
    void setUp() {
        P1.addPlayable(s1);
        P1.addPlayable(e1);
        P1.addPlayable(s2);
        P1.addPlayable(e2);

        P3.addPlayable(s1);
        P3.addPlayable(e1);
        P3.addPlayable(s2);
        P3.addPlayable(e2);

        P2.addPlayable(s1);
        P2.addPlayable(e1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addPlayable1() {
        P2.addPlayable(s2);
        P2.addPlayable(e2);
        assertEquals(P1,P2);
    }


    @Test
    void removePlayable1() {
        P1.removePlayable(2);
        P1.removePlayable(2);
        assertEquals(P1,P2);
    }

    @Test
    void removePlayable2() {
        P1.removePlayable(3);
        P1.removePlayable(2);
        assertEquals(P1,P2);
    }

    @Test
    void setName() {
        P1.setName("PlayList2");
        assertEquals(P1.getName(),"PlayList2");
    }

    @Test
    void shuffle() {
        PlayList test=P1.clone();
        assertEquals(P1,test);
        P1.shuffle();
        assertFalse(P1.equals(test));
    }

    //The undo() method reverts the state to the earlier one before the last state-modifying method was executed
    @Test
    void undo1() {

        P1.undo();
        P1.undo();
        assertEquals(P1,P2);
    }

    //Calling undo() multiple times consecutively undoes the methods in the reverse order of how they were executed.
    @Test
    void undo2() {
        P1.removePlayable(2);
        P1.addPlayable(e1);
        P1.removePlayable(0);
        P1.shuffle();
        P1.addPlayable(e2);
        P1.setName("PlayList2");

        for(int i=0;i<8;i++){
           P1.undo();
        }

        assertEquals(P1,P2);
        assertEquals(P1.getName(),"PlayList1");

    }

    //The method undo() does nothing if there are no methods to be undone.
    @Test
    void undo3() {
        PlayList test=P4.clone();
        P4.undo();
        assertEquals(P4,test);
    }

    //redo() will execute the last method that was undone
    @Test
    void redo1() {
        P3.addPlayable(e2);

        P1.redo();
        assertEquals(P3,P1);
    }


    //calling multiple redo() methods executes the undone methods in the reverse order of how they were undone.
    @Test
    void redo2() {

        P1.undo();
        P1.undo();
        P1.undo();
        P1.undo();
        P1.undo();
        P1.undo();
        P1.redo();
        P1.redo();
        assertEquals(P2,P1);
    }


    //The method redo() does nothing if there are no undone methods to be redone
    @Test
    void redo3() {

        P1.undo();
        P1.redo();
        //does nothing
        P1.redo();
        assertEquals(P3,P1);
    }

    //If another state-modifying method is called after undo(), redo() cannot redo any undone actions
    @Test
    void redo4() {
        P1.undo();
        P1.undo();
        P1.undo();
        P1.redo();
        P1.addPlayable(s2);
        P1.redo();
        P2.addPlayable(s2);
        P2.addPlayable(s2);
        assertEquals(P2,P1);
    }

    //it repeats the execution of that method.
    // For instance, redo() after calling removePlayable will remove the playable at the same index
    // if the index is not out of the bounds of the playlist.
    @Test
    void redo5() {
        P1.removePlayable(2);
        P1.redo();
        assertEquals(P2,P1);
    }

    @Test void mix(){
        P1.shuffle();
        P1.undo();
        P1.undo();
        P1.redo();
        P1.undo();
        P1.undo();
        P1.undo();
        P1.undo();
        P1.redo();
        P1.redo();
        P1.redo();
        P1.redo();
        assertEquals(P3,P1);
    }
}