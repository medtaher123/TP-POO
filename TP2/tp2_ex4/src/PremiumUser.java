import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PremiumUser implements User {
    private Playlist playlist = new Playlist(this);

    @Override
    public void listen(Song song) {
        song.playSong();
    }

    @Override
    public void addToPlaylist(Song song) {
        playlist.addSong(song);
    }

    public void shufflePlaylist() {
        playlist.shuffleSongs();
        System.out.println("Playlist shuffled.");
    }
}