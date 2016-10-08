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
    private static final String INSERT = "INSERT into FOOTBALLCLUBS values (?,?,?)";
    private static final String UPDATE = "UPDATE FOOTBALLCLUBS SET NAME=?, COUNTRY=? WHERE ID_CLUB=?";
    private static final String GET = "SELECT * from FOOTBALLCLUBS WHERE ID_CLUB=?";
    private static final String DELETE = "DELETE from FOOTBALLCLUBS WHERE ID_CLUB=?";
    private static final String GETALL = "SELECT * from FOOTBALLCLUBS";
    private final Connection connection;
    public OracleClubDAO(Connection connection) {
        this.connection = connection;
    }
    public int insert(FootballClub club){
        PreparedStatement stm = null;
        int id = -1;
        try{
            stm = connection.prepareStatement(INSERT, new String[] {"ID_CLUB"});
            stm.setInt(1,club.getClubId());
            stm.setString(2,club.getName());
            stm.setString(3,club.getCountry());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs!=null && rs.next()){
                id = rs.getInt(1);
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
    public void update (FootballClub club){
        PreparedStatement stm = null;
        try{
            stm = connection.prepareStatement(UPDATE);
            stm.setString(1,club.getName());
            stm.setString(2,club.getCountry());
            stm.setInt(3,club.getClubId());
            stm.executeQuery();
        }
        catch (SQLException e){
            LOGGER.error("SQLException: ",e);
        }
    }
    public FootballClub get(int id){
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(GET);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                FootballClub tmp = new FootballClub();
                tmp.setClubId(id);
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
        PreparedStatement stm = null;
        List<FootballClub> list = new ArrayList<FootballClub>();
        try {
            stm = connection.prepareStatement(GETALL);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                FootballClub tmp = new FootballClub();
                tmp.setClubId(rs.getInt("ID_CLUB"));
                tmp.setName(rs.getString("NAME"));
                tmp.setCountry(rs.getString("COUNTRY"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException: ",e);
        }
        return list;
    }
}
