package by.bsu.zenko.model;

/**
 * Created by 123 on 05.10.2016.
 */
public class Player {
    private int id_Player;
    private String name;
    private int id_Club;

    public int getId_Player() {
        return id_Player;
    }

    public void setId_Player(int id_Player) {
        this.id_Player = id_Player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_Club() {
        return id_Club;
    }

    public void setId_Club(int id_Club) {
        this.id_Club = id_Club;
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("ID: "+id_Player+"\n");
        tmp.append("NAME: "+name+"\n");
        tmp.append("ID_CLUB: "+id_Club+"\n");
        return tmp.toString();
    }
}
