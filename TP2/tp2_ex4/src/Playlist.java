import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Playlist {
    private List<Song> songs = new ArrayList<>();
    private User user;

    public Playlist(User user) {
        this.user = user;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void shuffleSongs() {
        Collections.shuffle(songs);
        System.out.println("Songs shuffled.");
    }
}