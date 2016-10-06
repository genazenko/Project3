package by.bsu.zenko.web;

import by.bsu.zenko.dao.DaoFactory;
import by.bsu.zenko.dao.OracleDaoFactory;
import by.bsu.zenko.dao.PlayerDAO;
import by.bsu.zenko.model.Player;
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
public class PlayersServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(PlayersServlet.class);
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("Player");
        ObjectMapper mapper = new ObjectMapper();
        Player pl = mapper.readValue(json, Player.class);
        DaoFactory daoFactory = new OracleDaoFactory();
        try{
            PlayerDAO playerDao = daoFactory.getPlayerDAO(daoFactory.getConnection());
            if(playerDao.insert(pl)==0) {
                resp.getOutputStream().print("Player sucsessful added");
            }
            else
                resp.getOutputStream().print("Player with id = "+pl.getId_Player()+" already exist");
        }
        catch(Exception e){
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoFactory daoFactory = new OracleDaoFactory();
        String param = req.getParameter("id");
        try {
            if (param!=null){
                Integer id = new Integer(param);
                PlayerDAO playerDAO = daoFactory.getPlayerDAO(daoFactory.getConnection());
                Player pl = playerDAO.get(id);
                ObjectMapper mapper = new ObjectMapper();
                resp.getOutputStream().print(mapper.writeValueAsString(pl));
            }
            else {
                PlayerDAO playerDAO = daoFactory.getPlayerDAO(daoFactory.getConnection());
                List<Player> list = playerDAO.getAll();
                ObjectMapper mapper = new ObjectMapper();
                resp.getOutputStream().print(mapper.writeValueAsString(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
