

public class Main {
    public static void main(String[] args) {
        // TASK: Write runner code to show your work

        //1.Each music library is characterized with a name. It may also contain a natural language description provided by the user.
        Library library=Library.instance();
        library.setName("Eggie's Library");
        library.setDescription("Eggie's Library has no books.");
        System.out.println(library.getName());
        System.out.println(library.getDescription());

        //2.should make sure that there is only one object of the library at any time during operation.
        Library library2=Library.instance();
        //should have the same output
        System.out.println(library2.getName());
        System.out.println(library2.getDescription());
        library.setName("Eggie's Libraryyyy");
        library.setDescription("Eggie's Libraryyyy has no books.");
        //should have the same changes
        System.out.println(library2.getName());
        System.out.println(library2.getDescription());

        //3.Two songs are considered the same if they have the same title and artist
        //similarly for podcasts but for the fields of name and host
        //should be true
        Song s1=Song.get("oJbK","eGgIe");
        Song s2=Song.get("OjBk","EgGiE");
        System.out.println(s1==s2);

        //should be true
        Podcast p1=Podcast.get("oJbK","eGgIe");
        Podcast p2=Podcast.get("OjBk","EgGiE");
        System.out.println(p1==p2);



        //4.The playlist can contain songs and/or episodes from podcasts.
        //Two playlists should be considered the same if the content is the same,
        //regardless of the name of the two playlists.
        Episode e1=new Episode(p1,"episode-one",1);
        Episode e2=new Episode(p1,"episode-three",3);
        Episode e3=new Episode(p2,"episode-two",2);
        Episode e4=new Episode(p2,"episode-four",4);

        PlayList l1=new PlayList("playlist1");
        PlayList l2=new PlayList("playlist2");

        l1.addPlayable(e1);
        l1.addPlayable(s1);
        l1.addPlayable(s2);
        l1.addPlayable(e4);
        l1.addPlayable(e3);
        l1.addPlayable(e2);

        //should play all songs and episode inside, there could be duplicate songs and episodes
        l1.play();

        l2.addPlayable(e1);
        l2.addPlayable(s1);
        l2.addPlayable(s2);
        l2.addPlayable(e4);
        l2.addPlayable(e3);
        l2.addPlayable(e2);

        //should be true
        System.out.println(l1.equals(l2));

        //Duplicate Songs/podcast/episode/playlist can't be added twice in the library.
        library.addPlayList(l1);
        library.addPodcast(p1);

        //should be 5 songs/episodes, 1 playlist, 1 podcast
        System.out.println(library.getNumOfSongsAndEpisodes());
        System.out.println(library.getNumOfPlaylists());
        System.out.println(library.getNumOfPodcasts());

        //number shouldn't change
        library.addSong(s1);
        library.addSong(s2);
        library.addPlayList(l2);
        library.addPodcast(p2);
        //should be 5 songs/episodes, 1 playlist, 1 podcast
        System.out.println(library.getNumOfSongsAndEpisodes());
        System.out.println(library.getNumOfPlaylists());
        System.out.println(library.getNumOfPodcasts());
    }
}
