import java.util.ArrayList;
import java.util.List;

class Album {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void listSongs() {
        System.out.println("Songs in the album:");
        for (Song song : songs) {
            System.out.println(song.getSongLength() + "s - " + song.getTitle() + " by " + song.getArtist());
        }
    }
}