public class PlayableManager {

    private Playable aPrototype = new Song("DefaultTitle","DefaultArtist"); // Default


    public void setPrototype(Playable pPrototype)
    {
        assert pPrototype!=null;

        aPrototype = pPrototype.clone();
    }

    public Playable createPlayable()
    {
        return aPrototype.clone();
    }
}
