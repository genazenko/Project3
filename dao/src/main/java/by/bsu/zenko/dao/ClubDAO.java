package by.bsu.zenko.dao;

import by.bsu.zenko.model.FootballClub;
import java.util.List;

/**
 * Created by 123 on 05.10.2016.
 */
public interface ClubDAO {
    public int insert(FootballClub club);
    public boolean delete(int id);
    public boolean update (FootballClub club);
    public FootballClub get(int id);
    public List<FootballClub> getAll();
}
