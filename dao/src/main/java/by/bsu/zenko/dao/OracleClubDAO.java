package by.bsu.zenko.dao;

import by.bsu.zenko.model.FootballClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 05.10.2016.
 */
public class OracleClubDAO implements ClubDAO {
    public static final Logger LOGGER = LoggerFactory.getLogger(OracleClubDAO.class);
    private final Connection connection;
    public OracleClubDAO(Connection connection) {
        this.connection = connection;
    }
    public int insert(FootballClub club){
        Statement stm = null;
        try{
            stm = connection.createStatement();
            String sql = "INSERT into FOOTBALLCLUBS values("+club.getId_Club()+", '"+club.getName()+"', '"+club.getCountry()+"')";
            if (CheckIfExist(club.getId_Club())){
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
            String sql = "DELETE from PLAYERS WHERE ID_CLUB="+id;
            stm.executeUpdate(sql);
            sql = "DELETE from FOOTBALLCLUBS WHERE ID_CLUB="+id;
            stm.executeUpdate(sql);
            return true;
        }
        catch(SQLException e){
            LOGGER.error("SQLException: ",e);
        }
        return false;
    }
    public boolean update (FootballClub club){
        Statement stm = null;
        try{
            if(!CheckIfExist(club.getId_Club())) return false;
            stm = connection.createStatement();
            String sql = "UPDATE FOOTBALLCLUBS SET NAME='"+club.getName()+"', COUNTRY="+club.getCountry()+" WHERE ID_CLUB="+club.getId_Club();
            stm.executeUpdate(sql);
            return true;
        }
        catch (SQLException e){
            LOGGER.error("SQLException: ",e);
        }
        return false;
    }
    public FootballClub get(int id){
        Statement stm = null;
        try {
            String sql = "SELECT * from FOOTBALLCLUBS WHERE ID_CLUB = "+id;
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                FootballClub tmp = new FootballClub();
                tmp.setId_Club(id);
                tmp.setName(rs.getString("NAME"));
                tmp.setCountry(rs.getString("COUNTRY"));
                return tmp;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return null;
    }
    public List<FootballClub> getAll(){
        Statement stm = null;
        List<FootballClub> list = new ArrayList<FootballClub>();
        try {
            String sql = "SELECT * from FOOTBALLCLUBS";
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                FootballClub tmp = new FootballClub();
                tmp.setId_Club(rs.getInt("ID_CLUB"));
                tmp.setName(rs.getString("NAME"));
                tmp.setCountry(rs.getString("COUNTRY"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return list;
    }
    boolean CheckIfExist(int id)throws SQLException{
        Statement stm = connection.createStatement();
        String sql = "SELECT * FROM FOOTBALLCLUBS WHERE ID_CLUB="+id;
        return stm.executeQuery(sql).next();
    }
}
