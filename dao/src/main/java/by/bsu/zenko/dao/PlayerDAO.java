package by.bsu.zenko.dao;

import by.bsu.zenko.model.Player;

import javax.servlet.ServletOutputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 123 on 05.10.2016.
 */
public interface PlayerDAO {
    public int insert(Player player);
    public void delete(int id);
    public void update (Player player);
    public Player get(int id) throws SQLException;
    public List<Player> getAll();
}
