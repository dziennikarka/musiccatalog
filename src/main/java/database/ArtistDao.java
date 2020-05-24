package database;

import model.Artist;

import java.util.List;

public interface ArtistDao {
    public List<Artist> getAllArtists();

    public Artist getArtist(int id);

    public boolean addArtist(Artist newArtist);

    public boolean removeArtist(Artist artist);
}
