import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {

    private List<Playable> aList = new LinkedList<>();
    private String aName;
    private LinkedList<PlaylistCommand> CommandList = new LinkedList<PlaylistCommand>();
    private int CommandListPtr=-1;
    private boolean stateModify;

    /**
     * Creates a new empty playlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public PlayList(String pName) {
        assert pName != null;
        aName = pName;
    }

    /**
     * @return The name of the playlist.
     */
    public String getName() {
        stateModify=false;
        return aName;
    }



    /**
     * Iterating through the playlist to play playable content.
     */
    @Override
    public void play() {
        for(Playable playable:aList){
            playable.play();
        }
        stateModify=false;
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
        PlaylistCommand c = getAddPlayable(pPlayable);
        if(CommandList.size()>CommandListPtr+1){
            for(int i=CommandListPtr+1;i<CommandList.size();i++){
                CommandList.remove(i);
            }
        }
        CommandList.add(c);
        CommandListPtr+=1;
        stateModify=true;
        c.execute();
    }

    private PlaylistCommand getAddPlayable(Playable pPlayable) {
        return new PlaylistCommand() {
            Playable newPlayable=pPlayable;

            @Override
            public Optional<Playable> execute() {
                aList.add(newPlayable);
                return Optional.empty();
            }
            @Override
            public void undo() {
                aList.remove(aList.size()-1);
            }
        };
    }

    /**
     * remove a playable from the Playlist given its index
     * @param pIndex
     *          the index of playable to be removed
     * @return the removed playable
     */
    public Playable removePlayable(int pIndex) {
        assert pIndex >= 0 && pIndex < aList.size();
        PlaylistCommand c = getRemovePlayable(pIndex);
        if(CommandList.size()>CommandListPtr+1){
            for(int i=CommandListPtr+1;i<CommandList.size();i++){
                CommandList.remove(i);
            }
        }
        CommandList.add(c);
        CommandListPtr+=1;
        stateModify=true;
        return aList.remove(pIndex);
    }

    private PlaylistCommand getRemovePlayable(int pIndex) {
        return new PlaylistCommand() {

            private Playable removedPlayable=aList.get(pIndex);
            private int index=pIndex;

            @Override
            public Optional<Playable> execute() {
                assert index >= 0 && index < aList.size();
                return Optional.ofNullable(aList.remove(index));
            }
            @Override
            public void undo() {
                aList.add(index,removedPlayable);
            }
        };
    }

    /**
     * modify the name of the playlist
     * @param pName
     *          the new name of the playlist
     */
    public void setName(String pName) {
        assert pName != null;
        PlaylistCommand c = getSetName(pName);
        if(CommandList.size()>CommandListPtr+1){
            for(int i=CommandListPtr+1;i<CommandList.size();i++){
                CommandList.remove(i);
            }
        }
        CommandList.add(c);
        CommandListPtr+=1;
        stateModify=true;
        c.execute();
    }

    private PlaylistCommand getSetName(String pName) {
        return new PlaylistCommand() {
            String oldname;
            String newname=pName;

            @Override
            public Optional<Playable> execute() {
                oldname=aName;
                aName = newname;
                return Optional.empty();
            }
            @Override
            public void undo() {
                aName=oldname;
            }
        };
    }

    /**
     * change the order how playlist play the playables it contains
     */
    public void shuffle() {
        PlaylistCommand c = getShuffle();
        if(CommandList.size()>CommandListPtr+1){
            for(int i=CommandListPtr+1;i<CommandList.size();i++){
                CommandList.remove(i);
            }
        }
        CommandList.add(c);
        CommandListPtr+=1;
        stateModify=true;
        c.execute();
    }

    private PlaylistCommand getShuffle() {
        return new PlaylistCommand() {

            private List<Playable> oldList = new LinkedList<>();

            @Override
            public Optional<Playable> execute() {
                for(Playable p : aList ){
                    oldList.add(p);
                }
                Collections.shuffle(aList);
                return Optional.empty();
            }
            @Override
            public void undo() {
                aList=oldList;
            }
        };
    }

    /**
     * undo the command
     */
    public void undo() {
        if(CommandListPtr!=-1){
            CommandList.get(CommandListPtr).undo();
            CommandListPtr-=1;
            stateModify=false;
        }
    }

    /**
     * redo the command
     */
    public Optional<Playable> redo() {
        if(CommandList.size()>CommandListPtr+1){
            CommandListPtr+=1;
            stateModify=false;
            return CommandList.get(CommandListPtr).execute();
        }else{
            if(CommandListPtr!=-1&&stateModify==true){
                PlaylistCommand c=CommandList.get(CommandListPtr);
                CommandList.add(c);
                CommandListPtr+=1;
                stateModify=false;
                return CommandList.get(CommandListPtr).execute();
            }
            stateModify=false;
            return Optional.empty();
        }

    }



    /**
     * Checks is two playlists are equal based on playable objects and their order
     *
     * @param o
     *            The object to compare a playlist to
     * @return    This method returns true if the playlist is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return this.aList.equals(playList.aList);
    }

    /**
     * Equal playlists have the same hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }

    /**
     * @Override the clone method for PlayList
     */
    @Override
    public PlayList clone()
    {
        try
        {
            PlayList clone = (PlayList) super.clone();
            clone.aList=new LinkedList<Playable>();
            for(int i=0;i<this.aList.size();i++ )
            {
                clone.aList.add(this.aList.get(i).clone());
            }
            stateModify=false;
            return clone;
        }
        catch(CloneNotSupportedException e )
        {
            assert false;
            return null;
        }
    }

}
