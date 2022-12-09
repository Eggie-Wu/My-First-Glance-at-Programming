import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private final LinkedList<Playable> aList = new LinkedList<>();
    /**
     * Creates a new empty playlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public PlayList(String pName) {
        assert pName != null;
    }

    public List<Playable> getList(){
        return Collections.unmodifiableList(aList);
    }

    /**
     * Adds a playable at the end of this playlist.
     *
     * @param pPlayable
     *            the content to add to the list
     * @pre pPlayable!=null;
     */
    public void addPlayable(Playable pPlayable) {
        assert pPlayable != null;
        aList.add(pPlayable);
    }


    @Override
    public void play() {
        for(int i=0;i<aList.size();i++){
            aList.get(i).play();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayList)) return false;
        PlayList playList = (PlayList) o;
        for(int i=0;i<this.getList().size();i++){
            if(!this.getList().get(i).equals(playList.getList().get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }
}
