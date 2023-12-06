import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        Song song1 = new Song("Song1", "Artist1", 180);
        Song song2 = new Song("Song2", "Artist2", 200);

        
        Album album1 = new Album();
        album1.addSong(song1);
        album1.addSong(song2);

        
        Artist artist1 = new Artist();
        artist1.addAlbum(album1);

        
        User freeUser = new FreeUser();
        User premiumUser = new PremiumUser();

        
        freeUser.listen(song1);
        freeUser.addToPlaylist(song2);

        premiumUser.listen(song1);
        premiumUser.addToPlaylist(song2);
        ((PremiumUser) premiumUser).shufflePlaylist();

        
        Playlist playlist1 = new Playlist(freeUser);
        playlist1.addSong(song1);
        playlist1.addSong(song2);

        Playlist playlist2 = new Playlist(premiumUser);
        playlist2.addSong(song1);
        playlist2.addSong(song2);

        
        playlist1.shuffleSongs();
        playlist2.shuffleSongs();
        
    }
}
