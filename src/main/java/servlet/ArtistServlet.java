package servlet;

import database.ArtistDao;
import database.JDBCArtistDao;
import model.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet {


    private ArtistDao dao = new JDBCArtistDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Artist> allArtists = this.dao.getAllArtists();

        request.setAttribute("artists", allArtists);

        request.getRequestDispatcher("/artists.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("artist");
        Artist artist = new Artist(name);
        dao.addArtist(artist);
        response.sendRedirect("/artists");


    }
}

