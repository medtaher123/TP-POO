
import java.util.ArrayList;
import java.util.List;

class Artist {
    private List<Album> albums = new ArrayList<>();

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void deleteAlbum(Album album) {
        albums.remove(album);
    }

    public void listAlbums() {
        System.out.println("Albums by the artist:");
        for (Album album : albums) {
            album.listSongs();
        }
    }
}