package by.bsu.zenko.dao;

import by.bsu.zenko.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 05.10.2016.
 */
public class OraclePlayerDAO implements PlayerDAO {
    public static final Logger LOGGER = LoggerFactory.getLogger(OraclePlayerDAO.class);
    private static final String INSERT = "INSERT into PLAYERS values (?, ?, ?)";
    private static final String UPDATE = "UPDATE PLAYERS SET NAME=?,ID_CLUB=? WHERE ID_PLAYER=?";
    private static final String GET = "SELECT * from PLAYERS WHERE ID_PLAYER=?";
    private static final String DELETE = "DELETE from PLAYERS WHERE ID_PLAYER=?";
    private static final String GETALL = "SELECT * from PLAYERS ORDER by ID_PLAYER";
    private final Connection connection;
    public OraclePlayerDAO(Connection connection){
        this.connection = connection;
    }
    public int insert(Player pl){
        PreparedStatement stm = null;
        int id=0;
        try{
            stm = connection.prepareStatement(INSERT);
            stm.setInt(1,pl.getPlayerId());
            stm.setString(2,pl.getName());
            stm.setInt(3,pl.getClubId());
            stm.executeQuery();
            stm = connection.prepareStatement(GETALL);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                id = rs.getInt("ID_PLAYER");
            }
        }
        catch(SQLException e){
            LOGGER.error("SQLException: ",e);
        }
        return id;
    }
    public void delete(int id){
        PreparedStatement stm = null;
        try{
            stm = connection.prepareStatement(DELETE);
            stm.setInt(1,id);
            stm.executeQuery();
        }
        catch(SQLException e){
            LOGGER.error("SQLException: ",e);
        }
    }
    public void update (Player pl){
        PreparedStatement stm = null;
        try{
            stm = connection.prepareStatement(UPDATE);
            stm.setString(1,pl.getName());
            stm.setInt(2,pl.getClubId());
            stm.setInt(3,pl.getPlayerId());
            stm.executeQuery();
        }
        catch (SQLException e){
            LOGGER.error("SQLException: ",e);
        }
    }
    public Player get(int id) {
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(GET);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Player tmp = new Player();
                tmp.setPlayerId(id);
                tmp.setName(rs.getString("NAME"));
                tmp.setClubId(rs.getInt("ID_CLUB"));
                return tmp;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return null;
    }
    public List<Player> getAll(){
        PreparedStatement stm = null;
        List<Player> list = new ArrayList<Player>();
        try {
            stm = connection.prepareStatement(GETALL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Player tmp = new Player();
                tmp.setPlayerId(rs.getInt("ID_PLAYER"));
                tmp.setName(rs.getString("NAME"));
                tmp.setClubId(rs.getInt("ID_CLUB"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return list;
    }
}
