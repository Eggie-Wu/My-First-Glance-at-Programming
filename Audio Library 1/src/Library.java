import java.util.HashSet;
import java.util.Set;

/**
 * Represents an Audio library, with individual Song titles, Podcasts and playlists.
 *
 * Use singleton design pattern
 */
public class Library {

    /**
     * Adds your design of fields for Library
     */
    private String Name;
    private String Description;
    private final Set<Playable> songsAndEpisodes=new HashSet<Playable>();
    private final Set<PlayList> Playlists=new HashSet<PlayList>();
    private final Set<Podcast> Podcasts=new HashSet<Podcast>();

    public int getNumOfSongsAndEpisodes(){
        return songsAndEpisodes.size();
    }
    public int getNumOfPlaylists(){
        return Playlists.size();
    }
    public int getNumOfPodcasts(){
        return Podcasts.size();
    }




    //Singleton
    private static final Library INSTANCE = new Library();

    private Library() {
        Name="Default";
        Description="Default";
    }

    //Singleton
    public static Library instance()
    {
        return INSTANCE;
    }

    public String getName() {return this.Name;}
    public String getDescription() {return this.Description;}
    public void setName(String pName) {assert pName!=null; this.Name = pName;}
    public void setDescription(String pDescription) {assert pDescription!=null; this.Description = pDescription;}



    /**
     * Adds a Song to the library. Duplicate Songs aren't added twice.
     *
     * @param pSong the Song to add.
     *
     * @pre pSong!= null
     */
    public void addSong(Song pSong) {
        // Please add you implementation here
        assert pSong!= null;

        //There won't be duplicate songs since equals() and Hashcode() has been override in Song class
        songsAndEpisodes.add(pSong);

    }

    /**
     * Adds a PlayList to the library. All Songs from the list are also added as individual Songs to the library.
     *
     * @param pList
     *            the PlayList to add
     * @pre pList!=null;
     */
    public void addPlayList(PlayList pList) {
       // Please add you implementation here
        assert pList!=null;

        //There won't be duplicate playlists since equals() and Hashcode() has been override in Playlists class
        if(Playlists.add(pList)){
            for(int i=0;i<pList.getList().size();i++){
                songsAndEpisodes.add(pList.getList().get(i));
            }
        }
    }

    /**
     * Adds a Podcast to the library. All Episodes from the list are also added as individual episodes to the library.
     *
     * @param pPodcast the Podcast to add
     *
     * @pre pPodcast!=null;
     */
    public void addPodcast(Podcast pPodcast) {
        // Please add you implementation here
        assert pPodcast!=null;

        //There won't be duplicate podcasts or episodes since equals() and Hashcode() has been override in Podcast/Episode class
        if(Podcasts.add(pPodcast)){
            for(int i=0;i<pPodcast.getSize();i++){
                songsAndEpisodes.add(pPodcast.getEpisode(i));
            }
        }
    }

}
