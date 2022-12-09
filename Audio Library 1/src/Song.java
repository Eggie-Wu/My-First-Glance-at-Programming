import java.util.*;

/**
 * Represents a single Song, with at least a title, and an artist name.
 */
public class Song implements Playable{

    private String aTitle;
    private String aArtist;

    private static final ArrayList<Song> Songs = new ArrayList<Song>();

    /**
     * Creates a Song.
     * Add your constructor here
     */

    //private constructor
    private Song(String pTitle,String pArtist){
        aTitle=pTitle.toUpperCase();
        aArtist=pArtist.toUpperCase();
    }

    //We use lazy initialization since we don't know the actual song
    //so there's no static initialization block

    /**
     * @param pTitle The title of the requested song.
     * @param pArtist The artist of the requested song.
     * @return The unique song instance with pTitle and pArtist
     * @pre pTitle != null && pArtist != null
     */
    public static Song get(String pTitle, String pArtist)
    {
        assert pTitle != null && pArtist != null;

        pTitle=pTitle.toUpperCase();
        pArtist=pArtist.toUpperCase();

        for(int i=0;i<Songs.size();i++){
            if(Songs.get(i).aTitle.equals(pTitle)&&Songs.get(i).aArtist.equals(pArtist)){
                return Songs.get(i);
            }
        }

        Song newSong=new Song(pTitle,pArtist);
        Songs.add(newSong);
        return newSong;
    }

    /**
     * @return The title of the song.
     */
    public String getTitle()
    {
        return aTitle;
    }

    /**
     * @return The artist of the song.
     */
    public String getArtist()
    {
        return aArtist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return aTitle.equals(song.aTitle) && aArtist.equals(song.aArtist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aTitle, aArtist);
    }

    @Override
    public void play() {
        // Just a stub.
        // We don't expect you to implement an actual music player!
        System.out.println("Now playing " + aTitle+" by "+aArtist);
    }

}