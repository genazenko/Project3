package by.bsu.zenko.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by 123 on 05.10.2016.
 */
public interface DaoFactory {
    public Connection getConnection() throws SQLException;
    public ClubDAO getClubDAO(Connection con);
    public PlayerDAO getPlayerDAO(Connection con);
}
