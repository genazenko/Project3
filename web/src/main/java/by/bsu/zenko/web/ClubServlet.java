package by.bsu.zenko.web;

import by.bsu.zenko.dao.ClubDAO;
import by.bsu.zenko.dao.DaoFactory;
import by.bsu.zenko.dao.OracleDaoFactory;
import by.bsu.zenko.model.FootballClub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 123 on 05.10.2016.
 */
public class ClubServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ClubServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory daoFactory = new OracleDaoFactory();
        String param = req.getParameter("id");
        try {
            if(param!=null){
                Integer id = new Integer(param);
                ClubDAO clubDAO = daoFactory.getClubDAO(daoFactory.getConnection());
                FootballClub club = clubDAO.get(id);
                ObjectMapper mapper = new ObjectMapper();
                resp.getOutputStream().print(mapper.writeValueAsString(club));
            }
            else {
                ClubDAO clubDAO = daoFactory.getClubDAO(daoFactory.getConnection());
                List<FootballClub> list = clubDAO.getAll();
                ObjectMapper mapper = new ObjectMapper();
                resp.getOutputStream().print(mapper.writeValueAsString(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
