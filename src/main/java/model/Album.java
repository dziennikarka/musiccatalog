package model;

public class Album {
    private int id;
    private String title;
    private int artistId;

    public Album(String title, int artistId) {
        this.title = title;
        this.artistId = artistId;
    }

    public Album(int id, String title, int artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getArtistId(){return this.artistId;}
}
