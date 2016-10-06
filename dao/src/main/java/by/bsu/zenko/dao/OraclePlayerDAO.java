package by.bsu.zenko.dao;

import by.bsu.zenko.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 05.10.2016.
 */
public class OraclePlayerDAO implements PlayerDAO {
    public static final Logger LOGGER = LoggerFactory.getLogger(OraclePlayerDAO.class);
    private final Connection connection;
    public OraclePlayerDAO(Connection connection){
        this.connection = connection;
    }
    public int insert(Player pl){
        Statement stm = null;
        try{
            stm = connection.createStatement();
            String sql = "INSERT into PLAYERS values("+pl.getId_Player()+", '"+pl.getName()+"',"+pl.getId_Club()+")";
            if (CheckIfExist(pl.getId_Player())){
                return -1;
            }
            else{
                stm.executeUpdate(sql);
            }
        }
        catch(SQLException e){
            LOGGER.error("SQLException: ",e);
        }
        return 0;
    }
    public boolean delete(int id){
        Statement stm = null;
        try{
            if(!CheckIfExist(id)) return false;
            stm = connection.createStatement();
            String sql = "DELETE from PLAYERS WHERE ID_PLAYER="+id;
            stm.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            LOGGER.error("SQLException: ",e);
        }
        return false;
    }
    public boolean update (Player pl){
        Statement stm = null;
        try{
            if(!CheckIfExist(pl.getId_Player())) return false;
            stm = connection.createStatement();
            String sql = "UPDATE PLAYERS SET NAME='"+pl.getName()+"', ID_CLUB="+pl.getId_Club()+" WHERE ID_PLAYER="+pl.getId_Player();
            stm.executeUpdate(sql);
            return true;
        }
        catch (SQLException e){
            LOGGER.error("SQLException: ",e);
        }
        return false;
    }
    public Player get(int id) {
        Statement stm = null;
        try {
            String sql = "SELECT * from PLAYERS WHERE ID_PLAYER = "+id;
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Player tmp = new Player();
                tmp.setId_Player(id);
                tmp.setName(rs.getString("NAME"));
                tmp.setId_Club(rs.getInt("ID_CLUB"));
                return tmp;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return null;
    }
    public List<Player> getAll(){
        Statement stm = null;
        List<Player> list = new ArrayList<Player>();
        try {
            String sql = "SELECT * from PLAYERS";
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Player tmp = new Player();
                tmp.setId_Player(rs.getInt("ID_PLAYER"));
                tmp.setName(rs.getString("NAME"));
                tmp.setId_Club(rs.getInt("ID_CLUB"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return list;
    }
    boolean CheckIfExist(int id)throws SQLException{
        Statement stm = connection.createStatement();
        String sql = "SELECT * FROM PLAYERS WHERE ID_PLAYER="+id;
        return stm.executeQuery(sql).next();
    }
}
