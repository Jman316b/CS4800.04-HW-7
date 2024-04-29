import java.util.ArrayList;
import java.util.List;

public class SongServer {
    private ArrayList<Song> songs;

    public SongServer() {
        this.songs = new ArrayList<>();
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Song searchById(Integer songID) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        for (Song song : songs) {
            if (song.getId() == songID) {
                return song;
            }
        }
        return null;
    }

    public List<Song> searchByTitle(String title) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        ArrayList<Song> outPut = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                outPut.add(song);
            }
        }
        return outPut;
    }

    public List<Song> searchByAlbum(String album) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}

        ArrayList<Song> outPut = new ArrayList<>();
        for (Song song : songs) {
            if (song.getAlbum().equals(album)) {
                outPut.add(song);
            }
        }
        return outPut;
    }
}
