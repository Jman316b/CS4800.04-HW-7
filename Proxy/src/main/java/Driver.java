public class Driver {
    public static void main(String[] args) {
        SongServer songServer = new SongServer();
        StreamingApp app = new StreamingApp(songServer);

        Song song1 = new Song(1, "song1", "artist1", "album1", 1);
        songServer.addSong(song1);

        Song song2 = new Song(2, "song2", "artist2", "album2", 2);
        songServer.addSong(song2);

        Song song3 = new Song(3, "song3", "artist3", "album3", 3);
        songServer.addSong(song3);

        Song song4 = new Song(4, "song3", "artist4", "album4", 4);
        songServer.addSong(song4);

        Song song5 = new Song(5, "song5", "artist5", "album5", 5);
        songServer.addSong(song5);

        Song song6 = new Song(6, "song6", "artist6", "album5", 6);
        songServer.addSong(song6);

        System.out.println("\n\nSearch Before Cache");
        System.out.println("Song by Id");
        System.out.println(app.searchById(1).toString());

        System.out.println("\nSong by Title");
        System.out.println(app.searchByTitle("song3"));

        System.out.println("\nSong by Album");
        System.out.println(app.searchByAlbum("album5").toString());


        System.out.println("\n\nSame Search After Cache");
        System.out.println("Song by Id");
        System.out.println(app.searchById(1).toString());

        System.out.println("\nSong by Title");
        System.out.println(app.searchByTitle("song3"));

        System.out.println("\nSong by Album");
        System.out.println(app.searchByAlbum("album5").toString());
    }
}
