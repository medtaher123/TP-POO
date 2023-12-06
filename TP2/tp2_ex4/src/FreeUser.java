import java.util.ArrayList;
import java.util.List;

class FreeUser implements User {
    private Playlist playlist = new Playlist(this);

    @Override
    public void listen(Song song) {
        song.playSong();
    }

    @Override
    public void addToPlaylist(Song song) {
        playlist.addSong(song);
    }
}