package database;

import model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCArtistDao implements ArtistDao {

    private final Database db = new Database();

    @Override
    public List<Artist> getAllArtists() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        List<Artist> artists = new ArrayList<>();

        try {
            // connection with DB
            connection = this.db.connect();

            // making the querry "SELECT * FROM Artist"
            statement = connection.prepareStatement("SELECT * FROM Artist");

            // executing the querry
            results = statement.executeQuery();

            // käydään läpi tuloksena saadut rivit ja muodostetaan niitä vastaavat oliot
            while (results.next()) {
                int id = results.getInt("ArtistId");
                String name = results.getString("name");

                // luodaan riviä vastaava olio:
                Artist newArtist = new Artist(id, name);

                artists.add(newArtist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.db.close(connection, statement, results);
        }
        return artists;
    }

    @Override
    public Artist getArtist(int id) {
        // unohdetaan hetkeksi suorituskyky (joudumme lataamaan kaikki vaikka haluamme
        // vain yhden):
        List<Artist> allArtists = this.getAllArtists();
        for (Artist artist : allArtists) {
            if (id == artist.getId()) {
                return artist;
            }
        }
        return null;
    }

    @Override
    public boolean addArtist(Artist newArtist) {
        String sql = "INSERT INTO Artist (name ) VALUES (?)";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet ids = null;

        try {
            connection = this.db.connect();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newArtist.getName());
            int rows = statement.executeUpdate();
            if (rows == 1) {
                ids = statement.getGeneratedKeys();
                ids.next();
                int generatedId = ids.getInt(1);
                newArtist.setId(generatedId);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.db.close(connection, statement, ids);
        }
        return false;
    }

    @Override
    public boolean removeArtist(Artist artist) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.db.connect();
            statement = connection.prepareStatement("DELETE FROM Artist WHERE id = ?");
            statement.setInt(1, artist.getId());
            int rows = statement.executeUpdate();
            if (rows == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.db.close(connection, statement, null);
        }
        return false;
    }
}
