import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single Podcast, with at least a name and a host. Each Podcast aggregates episodes.
 */
public class Podcast{

    private String aName;
    private String aHost;

    final private List<Episode> aEpisodes = new ArrayList<>();

    private static final ArrayList<Podcast> pocasts = new ArrayList<Podcast>();

    /**
     * Creates a Podcast
     * Add your constructor below
     *
     */
    //private constructor
    private Podcast(String pName,String pHost){
        aName=pName.toUpperCase();
        aHost=pHost.toUpperCase();

    }

    //We use lazy initialization since we don't know the actual podcast
    //so there's no static initialization block

    /**
     * @param pName The title of the requested podcast.
     * @param pHost The artist of the requested podcast.
     * @return The unique podcast instance with pName and pHost
     * @pre pName != null && pHost != null
     */
    public static Podcast get(String pName, String pHost)
    {
        assert pName != null && pHost != null;

        pName=pName.toUpperCase();
        pHost=pHost.toUpperCase();

        for(int i=0;i<pocasts.size();i++){
            if(pocasts.get(i).aName.equals(pName)&&pocasts.get(i).aHost.equals(pHost)){
                return pocasts.get(i);
            }
        }

        Podcast newPodcast=new Podcast(pName,pHost);
        pocasts.add(newPodcast);
        return newPodcast;
    }

    public int getSize(){
        return aEpisodes.size();
    }


    /**
     * Add one episode to the podcast
     * @param pEpisode to be put into the podcast
     * @pre pEpisode was not in the podcast
     */
    protected void addEpisode(Episode pEpisode) {
        if(!aEpisodes.contains(pEpisode)) {
            aEpisodes.add(pEpisode);
        }
    }

    /**
     * retrieve one episode from the podcast
     * @param pIndex
     * @pre pIndex>=0&&pIndex<aEpisodes.size()
     */

    public Episode getEpisode(int pIndex) {
        assert pIndex>=0&&pIndex<aEpisodes.size();
        return aEpisodes.get(pIndex);
    }


    public String getName() {
        return aName;
    }

    public String getaHost() {
        return aHost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Podcast)) return false;
        Podcast podcast = (Podcast) o;
        return aName.equals(podcast.aName) && aHost.equals(podcast.aHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aName, aHost);
    }
}