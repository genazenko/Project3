package by.bsu.zenko.web;

import by.bsu.zenko.dao.DaoFactory;
import by.bsu.zenko.dao.OracleDaoFactory;
import by.bsu.zenko.dao.OraclePlayerDAO;
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
    private ObjectMapper mapper = new ObjectMapper();
    private DaoFactory daoFactory = new OracleDaoFactory();
    private PlayerDAO playerDAO;
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("Player");
        Player pl = mapper.readValue(json, Player.class);
        try{
            playerDAO = daoFactory.getPlayerDAO(daoFactory.getConnection());
            resp.getOutputStream().print("Player with playerId = "+playerDAO.insert(pl)+" sucsessful added");
        }
        catch(Exception e){
            LOGGER.error("ERROR: ",e);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        try {
            if (param!=null){
                Integer id = new Integer(param);
                playerDAO = daoFactory.getPlayerDAO(daoFactory.getConnection());
                Player pl = playerDAO.get(id);
                resp.getOutputStream().print(mapper.writeValueAsString(pl));
            }
            else {
                playerDAO = daoFactory.getPlayerDAO(daoFactory.getConnection());
                List<Player> list = playerDAO.getAll();
                resp.getOutputStream().print(mapper.writeValueAsString(list));
            }
        } catch (Exception e) {
            LOGGER.error("ERROR: ",e);
        }
    }
}
