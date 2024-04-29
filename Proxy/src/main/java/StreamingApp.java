import java.util.ArrayList;
import java.util.List;

public class StreamingApp implements SongService {
    private SongServer songServer;
    private List<Song> cachedSongs;

    public StreamingApp(SongServer songServer) {
        this.songServer = songServer;
        this.cachedSongs = new ArrayList<>();
    }

    @Override
    public Song searchById(Integer songID) {
        for (Song song : cachedSongs) {
            if (song.getId() == songID) {
                return song;
            }
        }
        Song serverResult = songServer.searchById(songID);
        cachedSongs.add(serverResult);
        return serverResult;
    }

    @Override
    public List<Song> searchByTitle(String title) {
        ArrayList<Song> songs = new ArrayList<>();
        for (Song song : cachedSongs) {
            if (song.getTitle().equals(title)) {
                songs.add(song);
            }
        }
        if (songs.isEmpty()) {
            List<Song> serverResult = songServer.searchByTitle(title);
            cachedSongs.addAll(serverResult);
            songs.addAll(serverResult);
        }
        return songs;
    }

    @Override
    public List<Song> searchByAlbum(String album) {
        ArrayList<Song> songs = new ArrayList<>();
        for (Song song : cachedSongs) {
            if (song.getAlbum().equals(album)) {
                songs.add(song);
            }
        }
        if (songs.isEmpty()) {
            List<Song> serverResult = songServer.searchByAlbum(album);
            cachedSongs.addAll(serverResult);
            songs.addAll(serverResult);
        }
        return songs;
    }
}
