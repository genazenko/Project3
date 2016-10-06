package by.bsu.zenko.dao;

import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.Locale;
import org.slf4j.Logger;

/**
 * Created by 123 on 05.10.2016.
 */
public class OracleDaoFactory implements DaoFactory {
    private static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "z143";
    private static final String PASSWORD = "SYSTEM";
    public static final Logger LOGGER = LoggerFactory.getLogger(OracleDaoFactory.class);
    public Connection getConnection() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        return DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
    }
    public ClubDAO getClubDAO(Connection con){
        return new OracleClubDAO(con);
    }
    public PlayerDAO getPlayerDAO(Connection con){
        return new OraclePlayerDAO(con);
    }

    public OracleDaoFactory(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Driver registration error: ",e);
        }
    }
}
